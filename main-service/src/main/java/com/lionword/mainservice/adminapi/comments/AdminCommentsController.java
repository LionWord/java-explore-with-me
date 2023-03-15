package com.lionword.mainservice.adminapi.comments;

import com.lionword.mainservice.entity.comment.Comment;
import com.lionword.mainservice.adminapi.comments.service.AdminCommentsService;
import com.lionword.mainservice.entity.comment.CommentAmendRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(path = "/admin/comments")
@RequiredArgsConstructor
public class AdminCommentsController {

    private final AdminCommentsService commentsService;
    /**
     * Admin endpoint, allowing to approve or reject comments for publication.
     * Every comment listed must have status WAITING_REVIEW.
     * Can simultaneously handle new and amended events, and handles them differently.
     * If a comment was rejected by admin before publication, it is deleted from the database.
     * If comment amendment was rejected, it rolls back to previous state, but remains in database
     * Otherwise, if amendment was approved, comment text is changed and "amended" switch to "true". Publication date remains the same, but amendment date is added.
     * If new comment is approved for publication, method sets current time as publication date and assigns it to entry.
     * If amendment is approved, replaces old text with amended text
     * If there are no such comment or event, throws NoSuchEntryException.
     * If admin tries to approve an already approved comment, throws AlreadyPublishedException.
     *
     * @param commentsIds List of comments id's as request parameter. Required.
     * @param action Admin action. Can take APPROVE or REJECT value. Otherwise, method throws InvalidInputException. Required.
     *
     */
    @PatchMapping(path = "/moderate")
    public List<Comment> moderateComments(@RequestParam List<Long> commentsIds,
                                   @RequestParam String action) {
        return commentsService.moderateComments(commentsIds, action);
    }
    @GetMapping(path = "/review")
    public List<Comment> getCommentsWaitingReview(@RequestParam(required = false) Long eventId,
                                                  @RequestParam(required = false, defaultValue = "0") int from,
                                                  @RequestParam(required = false, defaultValue = "10") int size) {
        return commentsService.getCommentsWaitingReview(eventId, from, size);
    }

    @GetMapping(path = "/amended")
    public List<Comment> getAmendedComments(@RequestParam(required = false) Long eventId,
                                                  @RequestParam(required = false, defaultValue = "0") int from,
                                                  @RequestParam(required = false, defaultValue = "10") int size) {
        return commentsService.getAmendedComments(eventId, from, size);
    }

    @GetMapping("/amends")
    public List<CommentAmendRequest> getAmendmentRequests(@RequestParam(required = false) Long eventId,
                                                          @RequestParam(required = false, defaultValue = "0") int from,
                                                          @RequestParam(required = false, defaultValue = "10") int size) {
        return commentsService.getAmendmentRequests(eventId, from, size);
    }

    @GetMapping(path = "/all")
    public List<Comment> getAllComments(@RequestParam Long eventId,
                                        @RequestParam(required = false, defaultValue = "1111-01-01 00:00:01") String publicationDateStart,
                                        @RequestParam(required = false, defaultValue = "9999-01-01 00:00:01") String publicationDateEnd,
                                        @RequestParam(required = false, defaultValue = "0") int from,
                                        @RequestParam(required = false, defaultValue = "10") int size) {
        return commentsService.getAllComments(eventId, publicationDateStart, publicationDateEnd, from, size);
    }

    @DeleteMapping(path = "/delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteComments (@RequestParam List<Long> commentsIds) {
        commentsService.deleteComments(commentsIds);
    }
}