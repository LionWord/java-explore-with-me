package com.lionword.mainservice.entity.comment;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;


@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "comments_amends")
public class CommentAmendRequest {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "comment_id", referencedColumnName = "id")
    private Comment comment;
    @Column(name = "new_text")
    @Length(min = 3, max = 512)
    private String newText;
}
