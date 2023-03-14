package com.lionword.mainservice.adminapi.comments.service;

import com.lionword.mainservice.adminapi.comments.repository.AdminCommentsRepository;
import com.lionword.mainservice.adminapi.events.repository.AdminEventsRepository;
import com.lionword.mainservice.apierror.ExceptionTemplates;
import com.lionword.mainservice.entity.comment.Comment;
import com.lionword.mainservice.entity.comment.CommentAdminAction;
import com.lionword.mainservice.entity.comment.CommentStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminCommentsServiceImpl {

    private final AdminCommentsRepository commentsRepo;
    private final AdminEventsRepository eventsRepo;

    public List<Comment> moderateComments(List<Long> commentsIds, String action) {

        //Check if admin action is correct
        CommentAdminAction adminAction = stringToAction(action);

        //Check if admin is trying to pass non-existent comments
        List<Comment> comments = commentsRepo.findAllById(commentsIds);

        if (comments.size() != commentsIds.size()) {
            ExceptionTemplates.commentNotFound();
        }

        //Check if any listed comment is already published
        checkCommentListValidity(comments);

        //Split comments to new and amended for different handling
        List<List<Long>> newAndAmended = splitCommentsToNewAndAmended(comments);

        //apply admin action to given comments
        doAdminAction(newAndAmended.get(0), newAndAmended.get(1), adminAction);
        return commentsRepo.findAllById(commentsIds);

    }

    public List<Comment> getCommentsWaitingReview(Long eventId, int from, int size) {
        if (eventId != null) {
            checkIfEventIsPresent(eventId);
            return commentsRepo.getCommentsWaitingReviewByEventId(eventId, PageRequest.of(from, size)).getContent();
        }  else {
            return commentsRepo.getAllCommentsWaitingReview(PageRequest.of(from, size)).getContent();
        }
    }

    public List<Comment> getAllComments(Long eventId, int from, int size) {
        if (eventId != null) {
            checkIfEventIsPresent(eventId);
            return commentsRepo.findAllByEventId(eventId, PageRequest.of(from, size)).getContent();
        } else {
            return commentsRepo.findAll(Pa)
        }

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
                .filter(comment -> comment.getStatus().equals(CommentStatus.PUBLISHED)
                        | comment.getStatus().equals(CommentStatus.AMENDED_PUBLISHED))
                .collect(Collectors.toList());

        if (wrongComments.size() > 0) {
            ExceptionTemplates.commentAlreadyPublished();
        }
    }

    private void doAdminAction(List<Long> newComments, List<Long> amendedComments, CommentAdminAction adminAction) {
        switch (adminAction) {
            case APPROVE:
                commentsRepo.publishNewComments(newComments);
                commentsRepo.setPublicationDate(newComments, LocalDateTime.now());
                commentsRepo.approveAmending(amendedComments);
                break;
            case REJECT:
                commentsRepo.deleteAllById(newComments);
                //maybe i need some rollback logic here
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
                .filter(comment -> comment.getStatus().equals(CommentStatus.AMENDING_WAITING_REVIEW))
                .map(Comment::getId)
                .collect(Collectors.toList());
        splitComments.add(newComments);
        splitComments.add(amendedComments);
        return splitComments;
    }


}
