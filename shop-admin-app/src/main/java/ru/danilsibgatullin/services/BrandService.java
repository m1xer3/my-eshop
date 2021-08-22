package ru.danilsibgatullin.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.danilsibgatullin.dto.BrandDto;
import ru.danilsibgatullin.dto.CategoryDto;
import ru.danilsibgatullin.interfaces.BrandInterface;
import ru.danilsibgatullin.interfaces.BrandRepository;
import ru.danilsibgatullin.models.Brand;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BrandService implements BrandInterface {

    private final BrandRepository brandRepository;

    @Autowired
    public BrandService(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    public List<BrandDto> findAll(Integer page, Integer size, String sortField) {
        return brandRepository.findAll(PageRequest.of(page,size, Sort.by(sortField))).stream()
                .map(brand -> new BrandDto(brand.getId(), brand.getBrandName()))
                .collect(Collectors.toList());
    }

    @Override
    public void save(BrandDto brandDto) {
        Brand brand = new Brand(brandDto.getId(),brandDto.getBrandName());
        brandRepository.save(brand);

    }

    @Override
    public void deleteById(Long id) {
        brandRepository.deleteById(id);

    }

    @Override
    public Optional<BrandDto> findById(Long id) {
        return brandRepository.findById(id).
                map(brand -> new BrandDto(brand.getId(),brand.getBrandName()));
    }
}
