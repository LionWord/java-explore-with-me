package com.lionword.mainservice.privateapi.comments.service;

import com.lionword.mainservice.apierror.ExceptionTemplates;
import com.lionword.mainservice.entity.comment.Comment;
import com.lionword.mainservice.entity.comment.CommentAmend;
import com.lionword.mainservice.entity.comment.CommentAmendRequest;
import com.lionword.mainservice.entity.comment.CommentShortDto;
import com.lionword.mainservice.privateapi.comments.repository.PrivateAmendsRepository;
import com.lionword.mainservice.privateapi.comments.repository.PrivateCommentsRepository;
import com.lionword.mainservice.privateapi.repository.PrivateEventsRepository;
import com.lionword.mainservice.privateapi.repository.PrivateUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PrivateCommentsServiceImpl implements PrivateCommentsService {


    private final PrivateCommentsRepository commentsRepo;
    private final PrivateAmendsRepository amendsRepo;
    private final PrivateEventsRepository eventsRepo;
    private final PrivateUserRepository userRepo;

    @Override
    public Comment sendComment(Long userId, Long eventId, CommentShortDto commentDto) {
        checkIfUserIsPresent(userId);
        checkIfEventIsPresent(eventId);
        Comment comment = dtoToComment(commentDto, userId, eventId);
        return commentsRepo.save(comment);
    }
    @Override
    public CommentAmendRequest amendComment(Long userId, Long commentId, CommentAmend amend) {
        checkIfUserIsPresent(userId);
        checkIfAuthor(userId, commentId);
        Comment comment = commentsRepo.findById(commentId).orElseThrow(ExceptionTemplates::commentNotFound);
        CommentAmendRequest request = new CommentAmendRequest();
        request.setNewText(amend.getAmendedText());
        request.setComment(comment);
        if (amendsRepo.findByCommentId(commentId).isPresent()) {
            return amendsRepo.alterText(commentId, amend.getAmendedText());
        } else {
            return amendsRepo.save(request);
        }
    }

    public void deleteComment(Long userId, Long commentId) {
        checkIfUserIsPresent(userId);
        checkIfCommentIsPresent(commentId);
        checkIfAuthor(userId, commentId);
        commentsRepo.deleteComment(commentId);
        if (amendsRepo.findByCommentId(commentId).isPresent()) {
            amendsRepo.deleteAmendment(commentId);
        }
    }

    @Override
    public List<Comment> getMyComments(Long userId, int from, int size) {
        checkIfUserIsPresent(userId);
        return commentsRepo.findAllByUserId(userId, PageRequest.of(from, size)).getContent();
    }



    //-------------------------Service methods---------------------------
    private void checkIfEventIsPresent(Long eventId) {
        eventsRepo.findById(eventId).orElseThrow(ExceptionTemplates::eventNotFound);
    }

    private void checkIfUserIsPresent(Long userId) {
        userRepo.findById(userId).orElseThrow(ExceptionTemplates::userNotFound);
    }

    private void checkIfCommentIsPresent(Long commentId) {
        commentsRepo.findById(commentId).orElseThrow(ExceptionTemplates::commentNotFound);
    }

    private void checkIfAuthor(Long userId, Long commentId) {
        if (commentsRepo.findById(commentId).get().getAuthor().getId() != userId) {
            ExceptionTemplates.notCommentAuthor();
        }
    }

    private Comment dtoToComment(CommentShortDto dto, Long userId, Long eventId) {
        Comment comment = new Comment();
        comment.setEventId(eventId);
        comment.setAuthor(userRepo.findById(userId).orElseThrow(ExceptionTemplates::userNotFound));
        comment.setText(dto.getText());
        return comment;
    }

}
