package com.lionword.mainservice.adminapi.compilations.service;

import com.lionword.mainservice.adminapi.compilations.repository.AdminCompilationsRepository;
import com.lionword.mainservice.adminapi.events.repository.AdminEventsRepository;
import com.lionword.mainservice.apierror.ExceptionTemplates;
import com.lionword.mainservice.entity.compilation.Compilation;
import com.lionword.mainservice.entity.compilation.CompilationDto;
import com.lionword.mainservice.entity.compilation.NewCompilationDto;
import com.lionword.mainservice.entity.compilation.UpdateCompilationRequest;
import com.lionword.mainservice.entity.event.EventFullDto;
import com.lionword.mainservice.entity.util.CompilationsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminCompilationsServiceImpl implements AdminCompilationsService {

    private final AdminCompilationsRepository compilationsRepository;
    private final AdminEventsRepository eventsRepository;

    public CompilationDto addCompilation(NewCompilationDto compilation) {
        Compilation newCompilation = new Compilation();
        newCompilation.setPinned(compilation.getPinned());
        newCompilation.setTitle(compilation.getTitle());
        if (compilation.getEvents().isEmpty()) {
            compilationsRepository.save(newCompilation);
            return CompilationsMapper.mapToDto(newCompilation);
        }
        List<EventFullDto> events = eventsRepository.findAllById(compilation.getEvents());
        newCompilation.setEvents(events);
        compilationsRepository.save(newCompilation);
        return CompilationsMapper.mapToDto(newCompilation);
    }

    public void deleteCompilation(long compId) {
        compilationsRepository.deleteById(compId);
    }

    public CompilationDto updateCompilation(long compId, UpdateCompilationRequest updatedCompilation) {
        Compilation compilation = compilationsRepository.findById(compId)
                .orElseThrow(ExceptionTemplates::compilationNotFound);
        if (updatedCompilation.getPinned() != null) {
            compilation.setPinned(updatedCompilation.getPinned());
        }
        if (updatedCompilation.getTitle() != null) {
            if (!updatedCompilation.getTitle().isBlank()) {
                compilation.setTitle(updatedCompilation.getTitle());
            }
        }

        if (updatedCompilation.getEvents().isEmpty()) {
            compilationsRepository.save(compilation);
            return CompilationsMapper.mapToDto(compilation);
        }
        List<EventFullDto> events = eventsRepository.findAllById(updatedCompilation.getEvents());
        compilation.setEvents(events);
        compilationsRepository.save(compilation);
        return CompilationsMapper.mapToDto(compilation);

    }

}
