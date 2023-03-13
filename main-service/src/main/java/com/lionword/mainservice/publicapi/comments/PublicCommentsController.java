package com.lionword.mainservice.publicapi.comments;

import com.lionword.mainservice.entity.comment.Comment;
import com.lionword.mainservice.publicapi.comments.repository.PublicCommentsRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/comments")
@RequiredArgsConstructor
public class PublicCommentsController {

    //Unauthorized user can only watch comments
    private final PublicCommentsRepository commentsRepo;

    @GetMapping("/{eventId}")
    public List<Comment> getEventComments(@PathVariable Long eventId,
                                          @RequestParam(required = false, defaultValue = "0") int from,
                                          @RequestParam(required = false, defaultValue = "10") int size) {
        //Return only published comments

    }
}
