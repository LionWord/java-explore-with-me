package com.lionword.mainservice.privateapi.comments.repository;

import com.lionword.mainservice.entity.comment.Comment;
import com.lionword.mainservice.privateapi.comments.repository.limitedparents.LimitedPrivateCommentsRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrivateCommentsRepository extends LimitedPrivateCommentsRepository<Comment, Long> {
}
