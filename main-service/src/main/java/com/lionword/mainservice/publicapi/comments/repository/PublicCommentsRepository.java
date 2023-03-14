package com.lionword.mainservice.publicapi.comments.repository;

import com.lionword.mainservice.entity.comment.Comment;
import org.springframework.stereotype.Repository;

@Repository
public interface PublicCommentsRepository extends LimitedPublicCommentsRepository<Comment, Long> {
}
