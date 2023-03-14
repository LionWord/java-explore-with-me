package com.lionword.mainservice.publicapi.comments.repository;

import com.lionword.mainservice.entity.comment.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import java.util.List;

@NoRepositoryBean
public interface LimitedPublicCommentsRepository<T, S> extends Repository<T, S> {
    Page<T> findAllByEventId(Long eventId, Pageable pageable);
}
