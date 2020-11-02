package com.moodyjun.expensetrackerapp.controller;

import com.moodyjun.expensetrackerapp.model.Category;
import com.moodyjun.expensetrackerapp.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @RequestMapping("/categories")
    public List<Category> getCategories(){
        return categoryService.findAllCategory();
    }

    @RequestMapping("/category/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable long id){
        return categoryService.findCategoryById(id)
                .map(response-> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/category")
    public ResponseEntity<Category> createCategory(@RequestBody Category category) throws URISyntaxException {
        return ResponseEntity.created(new URI("/api/category/"+category.getCid())).body(categoryService.createCategory(category));
    }

    @PutMapping("/category")
    public ResponseEntity<Category> updateCategory(@RequestBody Category category){
        return ResponseEntity.ok().body(categoryService.updateCategory(category));
    }

    @DeleteMapping("/category/{id}")
    @ResponseBody
    public String deleteCategory(@PathVariable long id ){
        return categoryService.deleteCategoryById(id);
    }
}
