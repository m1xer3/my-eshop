package ru.danilsibgatullin.interfaces;

import ru.danilsibgatullin.dto.CategoryDto;
import ru.danilsibgatullin.models.Category;


import java.util.List;
import java.util.Optional;

public interface CategoryInterface {

    List<CategoryDto> findAll(Integer page, Integer size, String sortField);

    void save(CategoryDto category);

    void deleteById(Long id);

    Optional<CategoryDto> findById(Long id);

}
