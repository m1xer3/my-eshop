package ru.danilsibgatullin.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.danilsibgatullin.controllers.dto.CategoryDto;
import ru.danilsibgatullin.controllers.dto.ProductDto;
import ru.danilsibgatullin.services.CategoryService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("category")
public class CategoryConroller {

    private final CategoryService categoryService;

    @Autowired
    public CategoryConroller(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/all")
    public List<CategoryDto> listPage(@RequestParam("page") Optional<Integer> page,
                                      @RequestParam("size") Optional<Integer> size,
                                      @RequestParam("sortField") Optional<String> sortField, Model model){
        return categoryService.findAll(
                page.orElse(1) - 1,
                size.orElse(5),
                sortField.filter(fld -> !fld.isBlank()).orElse("id"));

    }

    @GetMapping("/{id}")
    public String editCategory(@PathVariable("id") Long id, Model model){
        model.addAttribute("category",categoryService.findById(id));
        return "category_form";
    }


}
