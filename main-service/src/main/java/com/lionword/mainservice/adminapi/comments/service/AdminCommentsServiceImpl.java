package com.lionword.mainservice.adminapi.comments.service;

import com.lionword.mainservice.adminapi.comments.repository.AdminAmendCommentRequestsRepository;
import com.lionword.mainservice.adminapi.comments.repository.AdminCommentsRepository;
import com.lionword.mainservice.adminapi.events.repository.AdminEventsRepository;
import com.lionword.mainservice.apierror.ExceptionTemplates;
import com.lionword.mainservice.entity.comment.Comment;
import com.lionword.mainservice.entity.comment.CommentAdminAction;
import com.lionword.mainservice.entity.comment.CommentAmendRequest;
import com.lionword.mainservice.entity.comment.CommentStatus;
import com.lionword.mainservice.entity.util.InputValidator;
import com.lionword.mainservice.entity.util.TimeFormatter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminCommentsServiceImpl implements AdminCommentsService {

    private final AdminCommentsRepository commentsRepo;
    private final AdminEventsRepository eventsRepo;
    private final AdminAmendCommentRequestsRepository amendRepo;

    @Override
    public List<Comment> moderateComments(List<Long> commentsIds, String action) {

        //Check if admin action is correct
        CommentAdminAction adminAction = stringToAction(action);

        //Check if admin is trying to pass non-existent comments
        List<Comment> comments = returnListIfAllExist(commentsIds);

        //Check if any listed comment is already published
        checkCommentListValidity(comments);

        //Split comments to new and amended for different handling
        List<List<Long>> newAndAmended = splitCommentsToNewAndAmended(comments);

        //apply admin action to given comments
        doAdminAction(newAndAmended.get(0), newAndAmended.get(1), adminAction);
        return commentsRepo.findAllById(commentsIds);

    }
    @Override
    public List<Comment> getCommentsWaitingReview(Long eventId, int from, int size) {
        if (eventId != null) {
            checkIfEventIsPresent(eventId);
            return commentsRepo.getCommentsWaitingReviewByEventId(eventId, PageRequest.of(from, size)).getContent();
        }  else {
            return commentsRepo.getAllCommentsWaitingReview(PageRequest.of(from, size)).getContent();
        }
    }
    @Override
    public List<CommentAmendRequest> getAmendmentRequests(Long eventId, int from, int size) {
        if (eventId != 0) {
            return amendRepo.findAmendmentRequestsByEventId(eventId, PageRequest.of(from, size)).getContent();
        } else {
            return amendRepo.findAll(PageRequest.of(from, size)).getContent();
        }
    }

    @Override
    public List<Comment> getAmendedComments(Long eventId, int from, int size) {
        if (eventId != null) {
            checkIfEventIsPresent(eventId);
            return commentsRepo.getCommentsWaitingReviewByEventId(eventId, PageRequest.of(from, size)).getContent();
        }  else {
            return commentsRepo.getAllCommentsWaitingReview(PageRequest.of(from, size)).getContent();
        }
    }
    @Override
    public List<Comment> getAllComments(Long eventId, String publicationStart, String publicationEnd, int from, int size) {
            InputValidator.checkDateInput(publicationStart, publicationEnd);
            checkIfEventIsPresent(eventId);
            LocalDateTime start = LocalDateTime.parse(publicationStart, TimeFormatter.DEFAULT);
            LocalDateTime end = LocalDateTime.parse(publicationEnd, TimeFormatter.DEFAULT);
            Pageable pageable = PageRequest.of(from, size);
            return commentsRepo.findAllByCriteria(eventId, start, end, PageRequest.of(from, size)).getContent();
    }
    @Override
    public void deleteComments(List<Long> commentsIds) {
        commentsRepo.deleteAllById(commentsIds);
    }

    //____________________Service methods_________________


    private void checkIfEventIsPresent(Long eventId) {
        eventsRepo.findById(eventId).orElseThrow(ExceptionTemplates::eventNotFound);
    }

    private CommentAdminAction stringToAction(String action) {
        CommentAdminAction adminAction = null;
        try {
            adminAction = CommentAdminAction.valueOf(action);
        } catch (IllegalArgumentException e) {
            ExceptionTemplates.invalidAdminAction();
        }
        return adminAction;
    }

    private void checkCommentListValidity(List<Comment> comments) {
        List<Comment> wrongComments = comments.stream()
                .filter(comment -> comment.getStatus().equals(CommentStatus.PUBLISHED))
                .collect(Collectors.toList());

        if (wrongComments.size() > 0) {
            ExceptionTemplates.commentAlreadyPublished();
        }
    }

    private List<Comment> returnListIfAllExist (List<Long> commentsIds) {
        List<Comment> comments = commentsRepo.findAllById(commentsIds);
        if (comments.size() != commentsIds.size()) {
            ExceptionTemplates.commentNotFound();
        }
        return comments;
    }

    private void doAdminAction(List<Long> newComments, List<Long> amendedComments, CommentAdminAction adminAction) {
        switch (adminAction) {
            case APPROVE:
                if (newComments.size() > 0) {
                    doApprovingSequence(newComments);
                }
                if (amendedComments.size() > 0) {
                    doAmendingSequence(amendedComments);
                }
                break;
            case REJECT:
                if (newComments.size() > 0) {
                    commentsRepo.deleteAllById(newComments);
                }
                if (amendedComments.size() > 0) {
                    //maybe i need some rollback logic here
                }
                break;
        }
    }

    private List<List<Long>> splitCommentsToNewAndAmended(List<Comment> comments) {
        List<List<Long>> splitComments = new ArrayList<>();
        List<Long> newComments = comments.stream()
                .filter(comment -> comment.getStatus().equals(CommentStatus.WAITING_REVIEW))
                .map(Comment::getId)
                .collect(Collectors.toList());
        List<Long> amendedComments = comments.stream()
                .filter(Comment::isAmended)
                .map(Comment::getId)
                .collect(Collectors.toList());
        splitComments.add(newComments);
        splitComments.add(amendedComments);
        return splitComments;
    }

    private void doAmendingSequence(List<Long> commentsIds) {
        commentsRepo.setAmended(commentsIds);
        amendTexts(commentsIds);
        commentsRepo.setAmendmentDate(commentsIds, LocalDateTime.now());
        amendRepo.deleteAmendmentRequest(commentsIds);
    }

    private void doApprovingSequence(List<Long> commentsIds) {
        commentsRepo.approveForPublication(commentsIds);
        commentsRepo.setPublicationDate(commentsIds, LocalDateTime.now());
    }

    private void amendTexts(List<Long> commentsIds) {
        Map<Long, String> commentsAndNewTexts = new ConcurrentHashMap<>(commentsIds.size());
        amendRepo.findAmendmentRequestsByCommentsIds(commentsIds).stream()
                .peek(commentAmendRequest -> commentsAndNewTexts.put(commentAmendRequest.getComment().getId(), commentAmendRequest.getNewText()));
        for (Long l : commentsAndNewTexts.keySet()) {
            commentsRepo.amendText(l, commentsAndNewTexts.get(l));
        }
    }

}
