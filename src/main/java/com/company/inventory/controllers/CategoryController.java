package com.company.inventory.controllers;

import com.company.inventory.models.Category;
import com.company.inventory.response.CategoryResponseRest;
import com.company.inventory.services.ICategoryService;
import com.company.inventory.util.CategoryExcelExporter;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@CrossOrigin("*")
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

    @DeleteMapping("/categories/{id}")
    public ResponseEntity<CategoryResponseRest> deleteCategory(@PathVariable Long id){

        ResponseEntity<CategoryResponseRest> categoryResponse = categoryService.deleteById(id);
        return categoryResponse;

    }

    @GetMapping("/categories/filter/{name}")
    public ResponseEntity<CategoryResponseRest> searchCategoriesByName(@PathVariable String name){

        ResponseEntity<CategoryResponseRest> categoryResponse = categoryService.searchByName(name);
        return categoryResponse;

    }

    @GetMapping("/categories/export/excel")
    public void exportToExcel(HttpServletResponse response) throws IOException {

        response.setContentType("application/octet-stream");

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=result_category.xlsx";
        response.setHeader(headerKey, headerValue);

        ResponseEntity<CategoryResponseRest> categoryResponse = categoryService.search();

        CategoryExcelExporter excelExporter = new CategoryExcelExporter(categoryResponse.getBody().getCategoryResponse().getCategory());

        excelExporter.export(response);

    }

}
