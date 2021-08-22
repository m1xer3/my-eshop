package ru.danilsibgatullin.interfaces;

import org.springframework.data.domain.Page;
import ru.danilsibgatullin.dto.ProductDto;
import ru.danilsibgatullin.models.Product;
import ru.danilsibgatullin.models.ProductParams;


import java.util.List;
import java.util.Optional;

public interface ProductInterface {

    Page<ProductDto> findAll(Integer page, Integer size, String sortField);

    Optional<ProductDto> findById(Long id);

    void save(ProductDto product);

    void deleteById(Long id);
}
