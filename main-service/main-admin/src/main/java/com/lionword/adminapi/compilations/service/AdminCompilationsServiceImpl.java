package com.lionword.adminapi.compilations.service;

import com.lionword.adminapi.compilations.repository.AdminCompilationsRepository;
import com.lionword.adminapi.events.repository.AdminEventsRepository;
import com.lionword.entity.compilation.Compilation;
import com.lionword.entity.compilation.CompilationDto;
import com.lionword.entity.compilation.NewCompilationDto;
import com.lionword.entity.compilation.UpdateCompilationRequest;
import com.lionword.entity.event.EventFullDto;
import com.lionword.entity.event.EventShortDto;
import com.lionword.entity.util.CompilationsMapper;
import com.lionword.entity.util.EventsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminCompilationsServiceImpl implements AdminCompilationsService {

    AdminCompilationsRepository compilationsRepository;
    AdminEventsRepository eventsRepository;

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
        Compilation compilation = compilationsRepository.findById(compId).orElseThrow();
        compilation.setPinned(updatedCompilation.isPinned());
        compilation.setTitle(updatedCompilation.getTitle());
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
