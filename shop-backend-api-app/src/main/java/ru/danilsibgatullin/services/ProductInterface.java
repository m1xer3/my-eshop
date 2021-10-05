package ru.danilsibgatullin.services;

import org.springframework.data.domain.Page;
import ru.danilsibgatullin.controllers.dto.ProductDto;

import java.util.Optional;

public interface ProductInterface {
    Page<ProductDto> findAll(Integer page, Integer size, String sortField);

    Optional<ProductDto> findById(Long id);
}
