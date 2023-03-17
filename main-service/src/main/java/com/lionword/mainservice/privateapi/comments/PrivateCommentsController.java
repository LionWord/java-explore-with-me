package com.lionword.mainservice.privateapi.comments;

import com.lionword.mainservice.entity.comment.Comment;
import com.lionword.mainservice.entity.comment.CommentAmend;
import com.lionword.mainservice.entity.comment.CommentAmendRequest;
import com.lionword.mainservice.entity.comment.CommentShortDto;
import com.lionword.mainservice.privateapi.comments.service.PrivateCommentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/users/{userId}/comments")
@RequiredArgsConstructor
public class PrivateCommentsController {

    private final PrivateCommentsService commentsService;

    @PostMapping("/{eventId}/send")
    public Comment sendComment(@PathVariable Long userId,
                               @PathVariable Long eventId,
                               @RequestBody CommentShortDto comment) {
        return commentsService.sendComment(userId, eventId, comment);
    }

    @PatchMapping("/{commentId}/amend")
    public CommentAmendRequest amendComment(@PathVariable Long userId,
                                            @PathVariable Long commentId,
                                            @RequestBody CommentAmend amendment) {
        return commentsService.amendComment(userId, commentId, amendment);
    }

    @DeleteMapping("/{commentId}/delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteComment(@PathVariable Long userId,
                              @PathVariable Long commentId) {
        commentsService.deleteComment(userId, commentId);
    }

    @GetMapping("/all")
    public List<Comment> getMyComments(@PathVariable Long userId,
                                       @RequestParam(required = false, defaultValue = "0") int from,
                                       @RequestParam(required = false, defaultValue = "10") int size) {
        return commentsService.getMyComments(userId, from, size);
    }

}
