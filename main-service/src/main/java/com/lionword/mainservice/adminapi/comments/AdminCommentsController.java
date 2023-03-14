package com.lionword.mainservice.adminapi.comments;

import com.lionword.mainservice.entity.comment.Comment;
import com.lionword.mainservice.adminapi.comments.service.AdminCommentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/admin/{eventId}/comments")
@RequiredArgsConstructor
public class AdminCommentsController {

    private final AdminCommentsService commentsService;
    /**
     * Admin endpoint, allowing to approve or reject comments for publication.
     * If a comment was rejected by a admin before publication, it is deleted from the database.
     * If comment is AMENDING_WAITING_REVIEW and was rejected, it rolls back to previous state.
     * Otherwise, if amendment was approved, comment text is changed and comment status switches to AMENDED. Publication date remains the same.
     * If there are no such comment, throws NoSuchEntryException.
     */
    @PatchMapping
    public Comment moderateComments(@PathVariable Long eventId,
                                   @RequestParam List<Long> commentsIds,
                                   @RequestParam String action) {

    }
    @GetMapping
    public List<Comment> getCommentsWaitingReview(@PathVariable Long eventId,
                                                  @RequestParam(required = false, defaultValue = "0") int from,
                                                  @RequestParam(required = false, defaultValue = "10") int size) {

    }

    @GetMapping
    public List<Comment> getAllComments(@PathVariable Long eventId,
                                        @RequestParam(required = false, defaultValue = "0") int from,
                                        @RequestParam(required = false, defaultValue = "10") int size) {

    }
}