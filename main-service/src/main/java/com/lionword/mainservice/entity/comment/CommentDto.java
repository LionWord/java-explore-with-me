package com.lionword.mainservice.entity.comment;

import com.lionword.mainservice.entity.user.UserShortDto;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter
@Setter
public class CommentDto {
    public UserShortDto author;
    public String text;
    public LocalDateTime publicationDate;
    public CommentStatus status;

}
