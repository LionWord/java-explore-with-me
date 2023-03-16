package com.lionword.mainservice.privateapi.comments.repository.limitedparents;

import com.lionword.mainservice.entity.comment.Comment;
import com.lionword.mainservice.entity.comment.CommentShortDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface LimitedPrivateCommentsRepository <T, S> extends Repository<T, S> {
    T save(T comment);
    Optional<T> findById(Long commentId);

    @Query("SELECT c FROM Comment c " +
            "JOIN FETCH UserDto ud on c.author = ud " +
            "WHERE c.author.id = :userId " +
            "ORDER BY c.id desc ")
    Page<T> findAllByUserId(Long userId, Pageable pageable);
    @Modifying
    @Transactional
    @Query("DELETE FROM Comment c " +
            "WHERE c.id = :commentId")
    void deleteComment(Long commentId);
}
