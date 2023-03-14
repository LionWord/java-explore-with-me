package com.lionword.mainservice.adminapi.comments.repository;

import com.lionword.mainservice.entity.comment.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

public interface AdminCommentsRepository extends JpaRepository<Comment, Long> {
    @Query("SELECT c FROM Comment c " +
            "WHERE c.eventId = :eventdId " +
            "AND c.status = '0' or c.status = '2' " +
            "ORDER BY c.id DESC ")
    Page<Comment> getCommentsWaitingReviewByEventId(Long eventId, Pageable pageable);

    @Query("SELECT c from Comment c " +
            "WHERE c.status = '0' OR c.status = '2'")
    Page<Comment> getAllCommentsWaitingReview(Pageable pageable);
    Page<Comment> findAllByEventId(Long eventId, Pageable pageable);

    @Modifying
    @Transactional
    @Query("UPDATE Comment c " +
            "SET c.status = '1' " +
            "WHERE c.id IN :commentsIds")
    void publishNewComments(List<Long> newCommentsIds);

    @Modifying
    @Transactional
    @Query("UPDATE Comment c " +
            "SET c.status = '3' " +
            "WHERE c.id IN :commentsIds")
    void approveAmending(List<Long> amendedCommentsIds);

    @Modifying
    @Transactional
    @Query("UPDATE Comment c " +
            "SET c.publicationDate = :publicationDate " +
            "WHERE c.id IN :commentsIds")
    void setPublicationDate(List<Long> commentsIds, LocalDateTime publicationDate);

    @Query("DELETE FROM Comment c " +
            "WHERE c.id IN :commentsIds " +
            "AND c.status = '0'")
    void deleteNewCommentsById(List<Long> commentsIds);

}

