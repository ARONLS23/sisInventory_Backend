package com.company.inventory.services;

import com.company.inventory.models.Category;
import com.company.inventory.response.CategoryResponseRest;
import org.springframework.http.ResponseEntity;

public interface ICategoryService {
    //Esctructura de respuesta http y englobar respuesta "CategoryResponseRest"
    public ResponseEntity<CategoryResponseRest> search();

    public ResponseEntity<CategoryResponseRest> searchById(Long id);

    public ResponseEntity<CategoryResponseRest> save(Category category);

    public ResponseEntity<CategoryResponseRest> update(Category category, Long id);

    public ResponseEntity<CategoryResponseRest> deleteById(Long id);

    public ResponseEntity<CategoryResponseRest> searchByName(String name);

}
