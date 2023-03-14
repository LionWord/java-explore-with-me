package com.lionword.mainservice.adminapi.comments.service;

import com.lionword.mainservice.adminapi.comments.repository.AdminCommentsRepository;
import com.lionword.mainservice.adminapi.events.repository.AdminEventsRepository;
import com.lionword.mainservice.apierror.ExceptionTemplates;
import com.lionword.mainservice.entity.comment.Comment;
import com.lionword.mainservice.entity.comment.CommentAdminAction;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminCommentsServiceImpl {

    private final AdminCommentsRepository commentsRepo;
    private final AdminEventsRepository eventsRepo;

    public Comment moderateComments(Long eventId, List<Long> commentsIds, String action) {
        CommentAdminAction adminAction = null;
        try {
            adminAction = CommentAdminAction.valueOf(action);
        } catch (IllegalArgumentException e) {
            ExceptionTemplates.invalidAdminAction();
        }

        switch (adminAction) {
            case APPROVE:
                break;
            case REJECT:
                commentsRepo.deleteAllById(commentsIds);
                break;
        }
    }

    public List<Comment> getCommentsWaitingReview(Long eventId, int from, int size) {
        checkIfEventIsPresent(eventId);
        return commentsRepo.getCommentsWaitingReview(eventId, PageRequest.of(from, size)).getContent();
    }

    public List<Comment> getAllComments(Long eventId, int from, int size) {
        checkIfEventIsPresent(eventId);
        return commentsRepo.findAllByEventId(eventId, PageRequest.of(from, size)).getContent();
    }

    private void checkIfEventIsPresent(Long eventId) {
        eventsRepo.findById(eventId).orElseThrow(ExceptionTemplates::eventNotFound);
    }

}
