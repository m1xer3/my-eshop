package ru.danilsibgatullin.services;

import ru.danilsibgatullin.controllers.dto.CategoryDto;

import java.util.List;
import java.util.Optional;

public interface CategoryInteraface {

    List<CategoryDto> findAll(Integer page, Integer size, String sortField);

    Optional<CategoryDto> findById(Long id);
}
