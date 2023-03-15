package com.lionword.mainservice.adminapi.comments.repository;

import com.lionword.mainservice.entity.comment.CommentAmend;
import com.lionword.mainservice.entity.comment.CommentAmendRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AdminAmendCommentRequestsRepository extends JpaRepository<CommentAmendRequest, Long> {
    @Query ("DELETE FROM CommentAmendRequest car " +
            "WHERE car.comment.id IN :commentsIds")
    void deleteAmendmentRequest(List<Long> commentsIds);

    @Query ("SELECT car FROM CommentAmendRequest car " +
            "WHERE car.comment.eventId = :eventId ")
    Page<CommentAmendRequest> findAmendmentRequestsByEventId(Long eventId, Pageable pageable);

    @Query ("SELECT car FROM CommentAmendRequest car " +
            "WHERE car.comment.id IN :commentsIds ")
    List<CommentAmendRequest> findAmendmentRequestsByCommentsIds(List<Long> commentsIds);
}
