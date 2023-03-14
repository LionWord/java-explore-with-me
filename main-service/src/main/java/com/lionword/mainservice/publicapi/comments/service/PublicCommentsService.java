package com.lionword.mainservice.publicapi.comments.service;

import com.lionword.mainservice.entity.comment.CommentDto;

import java.util.List;

public interface PublicCommentsService {
    List<CommentDto> getComments(Long eventId, int from, int size);
}
