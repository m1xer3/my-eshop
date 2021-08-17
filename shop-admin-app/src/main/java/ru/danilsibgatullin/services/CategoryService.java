package ru.danilsibgatullin.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.danilsibgatullin.interfaces.CateforyInterface;
import ru.danilsibgatullin.interfaces.CategoryRepository;
import ru.danilsibgatullin.models.Category;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService implements CateforyInterface {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository){
        this.categoryRepository=categoryRepository;
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public void save(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public Optional<Category> findById(Long id) {
        return categoryRepository.findById(id);
    }
}
