package ru.danilsibgatullin.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.danilsibgatullin.controllers.dto.BrandDto;
import ru.danilsibgatullin.controllers.dto.CategoryDto;
import ru.danilsibgatullin.controllers.dto.ProductDto;
import ru.danilsibgatullin.interfaces.ProductRepository;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService implements ProductInterface {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Page<ProductDto> findAll(Integer page, Integer size, String sortField) {
        return productRepository.findAll(PageRequest.of(page, size, Sort.by(sortField)))
                .map(product -> new ProductDto(product.getId(),
                        product.getTitle(),
                        product.getDescription(),
                        product.getCost(),
                        new CategoryDto(product.getCategory().getId(), product.getCategory().getCategoryName()),
                        new BrandDto(product.getBrand().getId(),product.getBrand().getBrandName()),
                        product.getPictures().stream().map(picture ->picture.getId()).collect(Collectors.toList()),
                        product.getMainPicture())
                );
    }

    @Override
    public Optional<ProductDto> findById(Long id) {
        return productRepository.findById(id)
                .map(product -> new ProductDto(product.getId(),
                        product.getTitle(),
                        product.getDescription(),
                        product.getCost(),
                        new CategoryDto(product.getCategory().getId(), product.getCategory().getCategoryName()),
                        new BrandDto(product.getBrand().getId(),product.getBrand().getBrandName()),
                        product.getPictures().stream().map(picture ->picture.getId()).collect(Collectors.toList()),
                        product.getMainPicture()));
    }
}
