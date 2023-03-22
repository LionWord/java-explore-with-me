package com.lionword.mainservice.entity.comment;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

@Getter
@Setter
@RequiredArgsConstructor
public class CommentAmend implements Serializable {
    @Length(min = 3, max = 512)
    private String amendedText;
}
