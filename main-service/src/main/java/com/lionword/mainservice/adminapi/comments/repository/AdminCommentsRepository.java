package com.lionword.mainservice.adminapi.comments.repository;

import com.lionword.mainservice.entity.comment.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;

public interface AdminCommentsRepository extends JpaRepository<Comment, Long> {
    @Query("SELECT c FROM Comment c " +
            "WHERE c.eventId = :eventId " +
            "AND c.status = 'WAITING_REVIEW' " +
            "ORDER BY c.id DESC ")
    Page<Comment> getCommentsWaitingReviewByEventId(Long eventId, Pageable pageable);

    @Query("SELECT c from Comment c " +
            "WHERE c.status = 'WAITING_REVIEW' ")
    Page<Comment> getAllCommentsWaitingReview(Pageable pageable);

    @Query("SELECT c FROM Comment c " +
            "JOIN FETCH UserDto ud ON c.author=ud " +
            "WHERE " +
            "c.eventId = :eventId " +
            "AND c.publicationDate >= :publicationDateStart " +
            "AND c.publicationDate <= :publicationDateEnd " +
            "ORDER BY c.publicationDate DESC ")
    Page<Comment> findAllByCriteria(Long eventId, LocalDateTime publicationDateStart, LocalDateTime publicationDateEnd, Pageable pageable);

    @Modifying
    @Transactional
    @Query("UPDATE Comment c " +
            "SET c.amended = true " +
            "WHERE c.id IN :commentsIds")
    void setAmended(List<Long> commentsIds);

    @Modifying
    @Transactional
    @Query("UPDATE Comment c " +
            "SET c.amendmentDate = :amendmentDate " +
            "WHERE c.id IN :commentsIds")
    void setAmendmentDate (List<Long> commentsIds, LocalDateTime amendmentDate);
    @Modifying
    @Transactional
    @Query("UPDATE Comment c " +
            "SET c.text = :newText " +
            "WHERE c.id = :commentId")
    void amendText(Long commentId, String newText);

}

