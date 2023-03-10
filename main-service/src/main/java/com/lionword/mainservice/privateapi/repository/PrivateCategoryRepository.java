package com.lionword.mainservice.privateapi.repository;

import com.lionword.mainservice.entity.category.CategoryDto;
import com.lionword.mainservice.privateapi.repository.limitedparents.LimitedPrivateCategoryRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrivateCategoryRepository extends LimitedPrivateCategoryRepository<CategoryDto, Long> {
}
