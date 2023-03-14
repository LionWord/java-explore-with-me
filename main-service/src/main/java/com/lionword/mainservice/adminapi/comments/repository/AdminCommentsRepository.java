package com.lionword.mainservice.adminapi.comments.repository;

import com.lionword.mainservice.entity.comment.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AdminCommentsRepository extends JpaRepository<Comment, Long> {
    @Query("SELECT c FROM Comment c " +
            "WHERE c.eventId = :eventdId " +
            "AND c.status = '0' or c.status = '2' " +
            "ORDER BY c.id DESC ")
    Page<Comment> getCommentsWaitingReview(Long eventId, Pageable pageable);

    Page<Comment> findAllByEventId(Long eventId, Pageable pageable);

    @Query("UPDATE Comment c " +
            "SET c.status = '1' " +
            "WHERE c.id IN :commentsIds")
    void approveComments(List<Long> commentsIds);
}
