package com.moodyjun.expensetrackerapp.service;

import com.moodyjun.expensetrackerapp.model.Category;
import com.moodyjun.expensetrackerapp.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> findAllCategory(){
        return categoryRepository.findAll();
    }

    public Optional<Category> findCategoryById(long id){
        return categoryRepository.findById(id);
    }

    public Category findCategoryByName(String name){
        return categoryRepository.findByName(name);
    }

    public Category createCategory(Category category){
        return categoryRepository.save(category);
    }

    public List<Category> createCategories(List<Category> categories){
        return categoryRepository.saveAll(categories);
    }

    public Category updateCategory ( Category category){
        Category exitingCategory = findCategoryById(category.getCid()).orElse(null);
        if (exitingCategory!=null){
            exitingCategory.setName(category.getName());
        }
        return categoryRepository.save(exitingCategory);
    }

    public String deleteCategoryById (long id){
        categoryRepository.deleteById(id);
        return "Successfully delete category with id - "+id;
    }



}
