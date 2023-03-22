package com.lionword.mainservice.privateapi.comments.repository;

import com.lionword.mainservice.entity.comment.CommentAmendRequest;
import com.lionword.mainservice.privateapi.comments.repository.limitedparents.LimitedPrivateAmendsRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrivateAmendsRepository extends LimitedPrivateAmendsRepository<CommentAmendRequest, Long> {
}
