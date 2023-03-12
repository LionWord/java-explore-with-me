package com.lionword.mainservice.adminapi.categories.service;

import com.lionword.mainservice.adminapi.categories.repository.AdminCategoriesRepository;
import com.lionword.mainservice.adminapi.events.repository.AdminEventsRepository;
import com.lionword.mainservice.apierror.exceptions.ExceptionTemplates;
import com.lionword.mainservice.apierror.exceptions.HaveLinkedEventsException;
import com.lionword.mainservice.apierror.exceptions.NoSuchEntryException;
import com.lionword.mainservice.apierror.exceptions.NotUniqueCategoryNameException;
import com.lionword.mainservice.entity.category.CategoryDto;
import com.lionword.mainservice.entity.category.NewCategoryDto;
import com.lionword.mainservice.entity.event.EventFullDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminCategoriesServiceImpl implements AdminCategoriesService {

    private final AdminCategoriesRepository categoriesRepository;
    private final AdminEventsRepository eventsRepository;

    public CategoryDto addNewCategory(NewCategoryDto newCategory) {
        checkIfCategoryNameIsUnique(newCategory.getName());
        CategoryDto category = new CategoryDto();
        category.setName(newCategory.getName());
        return categoriesRepository.save(category);
    }

    public void deleteCategory(long catId) {
        checkIfGotConnectedEvents(catId);
        categoriesRepository.deleteById(catId);
    }

    public CategoryDto updateCategory(NewCategoryDto updatedCategory, long catId) {
        checkIfCategoryNameIsUnique(updatedCategory.getName());
        CategoryDto category = categoriesRepository.findById(catId)
                .orElseThrow(ExceptionTemplates::categoryNotFound);
        category.setName(updatedCategory.getName());
        return categoriesRepository.save(category);
    }

    //-----Service methods------

    private void checkIfCategoryNameIsUnique(String name) {
        if (categoriesRepository.findByName(name).isPresent()) {
            ExceptionTemplates.notUniqueName();
        }
    }

    private void checkIfGotConnectedEvents(long catId) {
        CategoryDto category = categoriesRepository.findById(catId)
                .orElseThrow(ExceptionTemplates::categoryNotFound);
        if (!category.getEvents().isEmpty()) {
            List<Long> eventsIds = category.getEvents().stream()
                    .map(EventFullDto::getId)
                    .collect(Collectors.toList());
            ExceptionTemplates.haveLinkedEvents(eventsIds);
        }
    }

}
