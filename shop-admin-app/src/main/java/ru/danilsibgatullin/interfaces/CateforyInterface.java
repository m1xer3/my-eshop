package ru.danilsibgatullin.interfaces;

import ru.danilsibgatullin.models.Category;


import java.util.List;
import java.util.Optional;

public interface CateforyInterface {

    List<Category> findAll();

    void save(Category category);

    void deleteById(Long id);

    Optional<Category> findById(Long id);

}
