package ru.danilsibgatullin.controllers;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.danilsibgatullin.dto.CategoryDto;
import ru.danilsibgatullin.models.Category;

@Component
public class StringCategoryConverter implements Converter<String, CategoryDto> {

    @Override
    public CategoryDto convert(String s) {
        String[] arr = s.split(";");
        return new CategoryDto(Long.parseLong(arr[0]), arr[1]);
    }
}
