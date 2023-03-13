package com.lionword.mainservice.publicapi.comments.repository;

import com.lionword.mainservice.entity.comment.Comment;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import java.util.List;

@NoRepositoryBean
public interface LimitedPublicCommentsRepository<T, S> extends Repository<T, S> {
    List<Comment> findAllByEventId();
}
