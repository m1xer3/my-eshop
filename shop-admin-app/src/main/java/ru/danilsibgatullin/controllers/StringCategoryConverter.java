package ru.danilsibgatullin.controllers;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.danilsibgatullin.models.Category;

@Component
public class StringCategoryConverter implements Converter<String, Category> {

    @Override
    public Category convert(String s) {
        String[] arr = s.split(";");
        return new Category(Long.parseLong(arr[0]), arr[1]);
    }
}
