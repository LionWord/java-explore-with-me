package com.lionword.mainservice.entity.comment;

import com.lionword.mainservice.entity.user.UserDto;
import com.lionword.mainservice.entity.user.UserShortDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
@Table(name = "comments")
@Getter
@Setter
@RequiredArgsConstructor
public class Comment {
    @Id
    @Column(name = "id")
    public Long id;
    @Column(name = "event_id")
    public Long eventId;
    @ManyToOne
    @JoinColumn(name = "author")
    public UserDto author;
    @NotBlank
    @Length(min = 3, max = 512)
    @Column(name = "text")
    public String text;
    @Column(name = "publication_date")
    public LocalDateTime publicationDate = LocalDateTime.now();
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    public CommentStatus status = CommentStatus.WAITING_REVIEW;
}
