package com.lionword.mainservice.adminapi.comments.service;

import com.lionword.mainservice.entity.comment.Comment;
import com.lionword.mainservice.entity.comment.CommentAmendRequest;

import java.util.List;

public interface AdminCommentsService {
    List<Comment> moderateComments(List<Long> commentsIds, String action);
    List<Comment> getCommentsWaitingReview(Long eventId, int from, int size);
    List<Comment> getAmendedComments(Long eventId, int from, int size);
    List<Comment> getAllComments(Long eventId, String publicationStart, String publicationEnd, int from, int size);
    List<CommentAmendRequest> getAmendmentRequests(Long eventId, int from, int size);
    void deleteComments(List<Long> commentsIds);

}
