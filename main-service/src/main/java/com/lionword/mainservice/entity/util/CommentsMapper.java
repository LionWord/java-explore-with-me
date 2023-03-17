package com.lionword.mainservice.entity.util;

import com.lionword.mainservice.entity.comment.Comment;
import com.lionword.mainservice.entity.comment.CommentDto;

public class CommentsMapper {
    public static CommentDto mapToDto(Comment comment) {
        CommentDto commentDto = new CommentDto();
        commentDto.setText(comment.getText());
        commentDto.setPublicationDate(comment.getPublicationDate());
        commentDto.setAuthor(UserMapper.mapToShort(comment.getAuthor()));
        return commentDto;
    }
}
