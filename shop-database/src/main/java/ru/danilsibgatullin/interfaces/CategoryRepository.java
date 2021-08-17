package ru.danilsibgatullin.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.danilsibgatullin.models.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
