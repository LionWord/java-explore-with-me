package com.lionword.mainservice.adminapi.comments;

import com.lionword.mainservice.entity.comment.Comment;
import com.lionword.mainservice.adminapi.comments.service.AdminCommentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/admin/comments")
@RequiredArgsConstructor
public class AdminCommentsController {

    private final AdminCommentsService commentsService;
    /**
     * Admin endpoint, allowing to approve or reject comments for publication.
     * Every comment listed must have status WAITING_REVIEW or AMENDING_WAITING_REVIEW.
     * Can simultaneously handle new and amended events, and handles them differently.
     * If a comment was rejected by admin before publication, it is deleted from the database.
     * If comment is AMENDING_WAITING_REVIEW and was rejected, it rolls back to previous state, but remains in database
     * Otherwise, if amendment was approved, comment text is changed and comment status switches to AMENDED_PUBLISHED. Publication date remains the same.
     * If new comment is approved for publication, method sets current time as publication date and assigns it to entry.
     * If amendment is approved, replaces old text with amended text
     * If there are no such comment or event, throws NoSuchEntryException.
     * If admin tries to approve an already approved comment, throws AlreadyPublishedException.
     *
     * @param commentsIds List of comments id's as request parameter. Required.
     * @param action Admin action. Can take APPROVE or REJECT value. Otherwise, method throws InvalidInputException. Required.
     *
     */
    @PatchMapping
    public List<Comment> moderateComments(@RequestParam List<Long> commentsIds,
                                   @RequestParam String action) {

    }
    @GetMapping
    public List<Comment> getCommentsWaitingReview(@RequestParam(required = false) Long eventId,
                                                  @RequestParam(required = false, defaultValue = "0") int from,
                                                  @RequestParam(required = false, defaultValue = "10") int size) {

    }

    @GetMapping
    public List<Comment> getAllComments(@RequestParam(required = false) Long eventId,
                                        @RequestParam(required = false, defaultValue = "0") int from,
                                        @RequestParam(required = false, defaultValue = "10") int size) {

    }

    @DeleteMapping
    public void deleteComments
}