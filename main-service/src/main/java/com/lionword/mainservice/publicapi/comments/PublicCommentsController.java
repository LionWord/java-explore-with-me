package com.lionword.mainservice.publicapi.comments;

import com.lionword.mainservice.entity.comment.CommentDto;
import com.lionword.mainservice.publicapi.comments.service.PublicCommentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/comments")
@RequiredArgsConstructor
public class PublicCommentsController {

    private final PublicCommentsService commentsService;

    /**
     * Public endpoint, allowing to watch comments by event id.
     * Displays only comments, approved by moderator (PUBLISHED).
     * If there are no comments yet, returns an empty list.
     * NOTICE: This is the only one comments-related endpoint, available to unauthorized users - they can't leave comments.
     */
    @GetMapping("/{eventId}")
    public List<CommentDto> getEventComments(@PathVariable Long eventId,
                                             @RequestParam(required = false, defaultValue = "0") int from,
                                             @RequestParam(required = false, defaultValue = "10") int size) {
        return commentsService.getComments(eventId, from, size);
    }
}
