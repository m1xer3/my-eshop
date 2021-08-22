package ru.danilsibgatullin.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.danilsibgatullin.models.Brand;

public interface BrandRepository extends JpaRepository<Brand,Long> {
}
