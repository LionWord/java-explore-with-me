package com.lionword.publicapi.compilations.service;

import com.lionword.entity.compilation.Compilation;
import com.lionword.entity.compilation.CompilationDto;
import com.lionword.entity.util.CompilationsMapper;
import com.lionword.publicapi.compilations.repository.PublicCompilationsRepository;
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
            compilations = compilationsRepository.findAllByPinned(pinned, PageRequest.of(from, size)).stream()
                    .map(CompilationsMapper::mapToDto)
                    .collect(Collectors.toList());
        } else {
            compilations = compilationsRepository.findAll(PageRequest.of(from, size)).stream()
                    .map(CompilationsMapper::mapToDto)
                    .collect(Collectors.toList());
        }
        return compilations;
    }

    public CompilationDto getCompilationById(long compId) {
        return CompilationsMapper.mapToDto(compilationsRepository.findById(compId).orElseThrow());
    }
}
