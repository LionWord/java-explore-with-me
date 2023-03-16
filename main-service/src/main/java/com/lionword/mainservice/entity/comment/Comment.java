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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "event_id")
    private Long eventId;
    @ManyToOne
    @JoinColumn(name = "author")
    private UserDto author;
    @NotBlank
    @Length(min = 3, max = 512)
    @Column(name = "text")
    private String text;
    @Column(name = "publication_date")
    private LocalDateTime publicationDate;
    @Column(name = "amended")
    private boolean amended = false;
    @Column(name = "amendment_date")
    private LocalDateTime amendmentDate;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private CommentStatus status = CommentStatus.WAITING_REVIEW;
}
