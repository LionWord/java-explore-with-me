package com.lionword.mainservice.entity.comment;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@RequiredArgsConstructor
public class CommentAmend {
    @Length(min = 3, max = 512)
    private String amendedText;
}
