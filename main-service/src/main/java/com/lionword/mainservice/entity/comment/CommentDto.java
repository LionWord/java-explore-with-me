package com.lionword.mainservice.entity.comment;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lionword.mainservice.entity.user.UserShortDto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CommentDto {
    public UserShortDto author;
    public String text;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    public LocalDateTime publicationDate;

}
