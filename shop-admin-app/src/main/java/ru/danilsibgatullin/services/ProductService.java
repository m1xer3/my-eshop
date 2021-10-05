package ru.danilsibgatullin.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.danilsibgatullin.dto.BrandDto;
import ru.danilsibgatullin.dto.CategoryDto;
import ru.danilsibgatullin.dto.ProductDto;
import ru.danilsibgatullin.exceptions.NotFoundException;
import ru.danilsibgatullin.interfaces.*;
import ru.danilsibgatullin.models.*;
import ru.danilsibgatullin.ProductSpecifications;


import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService implements ProductInterface {

    private final ProductRepository productRepository;

    private final CategoryRepository categoryRepository;

    private final BrandRepository brandRepository;

    private final  PictureServiceInterface pictureService;

    @Autowired
    public ProductService(ProductRepository productRepository,
                          CategoryRepository categoryRepository,
                          BrandRepository brandRepository,
                          PictureServiceInterface pictureService) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.brandRepository  = brandRepository;
        this.pictureService=pictureService;
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

    @Override
    @Transactional
    public void save(ProductDto productDto) {
        Product product = (productDto.getId() != null) ? productRepository.findById(productDto.getId())
                .orElseThrow(() -> new NotFoundException("")) : new Product();
        Category category = categoryRepository.findById(productDto.getCategory().getId())
                .orElseThrow(() -> new RuntimeException("Category not found"));
        Brand brand = brandRepository.findById(productDto.getBrand().getId())
                .orElseThrow(() -> new RuntimeException("Brand not found"));

        product.setTitle(productDto.getTitle());
        product.setCategory(category);
        product.setCost(productDto.getCost());
        product.setDescription(productDto.getDescription());
        product.setCategory(category);
        product.setBrand(brand);
        product.setMainPicture(productDto.getMainPicture());

        if (productDto.getNewPictures() != null) {
            for (MultipartFile newPicture : productDto.getNewPictures()) {
                try {
                    product.getPictures().add(new Picture(null,
                            newPicture.getOriginalFilename(),
                            newPicture.getContentType(),
                            pictureService.createPicture(newPicture.getBytes()),
                            product
                    ));
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }

        productRepository.save(product);
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }
}
