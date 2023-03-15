package com.lionword.mainservice.privateapi.comments.service;

import com.lionword.mainservice.entity.comment.Comment;
import com.lionword.mainservice.entity.comment.CommentAmend;
import com.lionword.mainservice.entity.comment.CommentAmendRequest;
import com.lionword.mainservice.entity.comment.CommentShortDto;

import java.util.List;

public interface PrivateCommentsService {

    Comment sendComment(Long userId, Long eventId, CommentShortDto commentDto);
    CommentAmendRequest amendComment(Long userId, Long commentId, CommentAmend amend);
    void deleteComment(Long userId, Long commentId);
    List<Comment> getMyComments(Long userId, int from, int size);
}
