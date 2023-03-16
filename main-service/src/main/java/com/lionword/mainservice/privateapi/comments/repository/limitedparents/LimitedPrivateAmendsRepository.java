package com.lionword.mainservice.privateapi.comments.repository.limitedparents;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@NoRepositoryBean
public interface LimitedPrivateAmendsRepository <T, S> extends Repository<T, S> {
    T save(T amendment);
    @Query("SELECT car FROM CommentAmendRequest car " +
            "JOIN FETCH Comment c ON car.comment = c " +
            "WHERE car.comment.id = :commentId ")
    Optional<T> findByCommentId(Long commentId);

    @Modifying
    @Transactional
    @Query ("UPDATE CommentAmendRequest car " +
            "SET car.newText = :newText " +
            "WHERE car.comment.id = :commentId ")
    void alterText(Long commentId, String newText);

    @Query ("DELETE FROM CommentAmendRequest car " +
            "WHERE car.comment.id = :commentId")
    void deleteAmendment(Long commentId);
}
