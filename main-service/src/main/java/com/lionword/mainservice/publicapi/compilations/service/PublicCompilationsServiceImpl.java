package com.lionword.mainservice.publicapi.compilations.service;

import com.lionword.mainservice.apierror.ExceptionTemplates;
import com.lionword.mainservice.entity.compilation.CompilationDto;
import com.lionword.mainservice.entity.util.CompilationsMapper;
import com.lionword.mainservice.publicapi.compilations.repository.PublicCompilationsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PublicCompilationsServiceImpl implements PublicCompilationsService {

    private final PublicCompilationsRepository compilationsRepository;

    public List<CompilationDto> getCompilations(Boolean pinned, int from, int size) {
        List<CompilationDto> compilations;
        if (pinned != null) {
            compilations = compilationsRepository.findAllByPinned(pinned, PageRequest.of(from, size)).getContent().stream()
                    .map(CompilationsMapper::mapToDto)
                    .collect(Collectors.toList());
        } else {
            compilations = compilationsRepository.findAll(PageRequest.of(from, size)).getContent().stream()
                    .map(CompilationsMapper::mapToDto)
                    .collect(Collectors.toList());
        }
        return compilations;
    }

    public CompilationDto getCompilationById(long compId) {
        return CompilationsMapper.mapToDto(compilationsRepository.findById(compId)
                .orElseThrow(ExceptionTemplates::compilationNotFound));
    }
}
