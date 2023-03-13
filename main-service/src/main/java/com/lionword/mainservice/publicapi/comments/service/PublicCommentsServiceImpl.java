package com.lionword.mainservice.publicapi.comments.service;

import com.lionword.mainservice.publicapi.comments.repository.PublicCommentsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PublicCommentsServiceImpl implements PublicCommentsService {

    private final PublicCommentsRepository commentsRepo;

}
