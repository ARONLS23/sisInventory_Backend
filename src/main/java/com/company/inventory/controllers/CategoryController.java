package com.company.inventory.controllers;

import com.company.inventory.models.Category;
import com.company.inventory.response.CategoryResponseRest;
import com.company.inventory.services.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;

    @GetMapping("/categories")
    public ResponseEntity<CategoryResponseRest> searchCategories(){

        ResponseEntity<CategoryResponseRest> categoryResponse = categoryService.search();
        return categoryResponse;

    }

    @GetMapping("/categories/{id}")
    public ResponseEntity<CategoryResponseRest> searchCategoriesById(@PathVariable Long id){

        ResponseEntity<CategoryResponseRest> categoryResponse = categoryService.searchById(id);
        return categoryResponse;

    }

    @PostMapping("/categories")
    public ResponseEntity<CategoryResponseRest> saveCategory(@RequestBody Category category){

        ResponseEntity<CategoryResponseRest> categoryResponse = categoryService.save(category);
        return categoryResponse;

    }

    @PutMapping("/categories/{id}")
    public ResponseEntity<CategoryResponseRest> updateCategory(@RequestBody Category category, @PathVariable Long id){

        ResponseEntity<CategoryResponseRest> categoryResponse = categoryService.update(category, id);
        return categoryResponse;

    }

}
