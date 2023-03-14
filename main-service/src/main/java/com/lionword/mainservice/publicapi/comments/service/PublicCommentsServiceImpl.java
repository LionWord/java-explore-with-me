package com.lionword.mainservice.publicapi.comments.service;

import com.lionword.mainservice.apierror.ExceptionTemplates;
import com.lionword.mainservice.entity.comment.CommentDto;
import com.lionword.mainservice.entity.comment.CommentStatus;
import com.lionword.mainservice.entity.util.CommentsMapper;
import com.lionword.mainservice.publicapi.comments.repository.PublicCommentsRepository;
import com.lionword.mainservice.publicapi.events.repository.PublicEventsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PublicCommentsServiceImpl implements PublicCommentsService {

    private final PublicCommentsRepository commentsRepo;
    private final PublicEventsRepository eventsRepo;

    @Override
    public List<CommentDto> getComments(Long eventId, int from, int size) {
        eventsRepo.findById(eventId).orElseThrow(ExceptionTemplates::eventNotFound);
        return commentsRepo.findAllByEventId(eventId, PageRequest.of(from, size))
                .getContent().stream()
                .filter(comment -> comment.getStatus().equals(CommentStatus.PUBLISHED)
                        | comment.getStatus().equals(CommentStatus.AMENDED))
                .map(CommentsMapper::mapToDto)
                .collect(Collectors.toList());
    }

}
