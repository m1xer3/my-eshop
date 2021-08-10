package ru.danilsibgatullin.interfaces;

import org.springframework.data.domain.Page;
import ru.danilsibgatullin.models.Product;
import ru.danilsibgatullin.models.ProductParams;


import java.util.List;
import java.util.Optional;

public interface ProductInterface {

    List<Product> findAll();

    Page<Product> findWithFilter(ProductParams productParams);

    Optional<Product> findById(Long id);

    void save(Product user);

    void deleteById(Long id);
}
