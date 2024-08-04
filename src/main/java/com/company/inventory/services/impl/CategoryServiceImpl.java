package com.company.inventory.services.impl;

import com.company.inventory.models.Category;
import com.company.inventory.repositories.ICategoryRepository;
import com.company.inventory.response.CategoryResponseRest;
import com.company.inventory.services.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryServiceImpl implements ICategoryService {

    @Autowired
    private ICategoryRepository categoryRepository;

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<CategoryResponseRest> search() {

        CategoryResponseRest categoryResponse = new CategoryResponseRest();

        try{

            List<Category> category = categoryRepository.findAll();

            categoryResponse.getCategoryResponse().setCategory(category);
            categoryResponse.setMetadata("Respuesta ok", "00", "Respuesta exitosa");

        }catch (Exception e){

            categoryResponse.setMetadata("Respuesta no ok", "-1", "Error al consultar");
            e.getStackTrace();
            return new ResponseEntity<CategoryResponseRest>(categoryResponse, HttpStatus.INTERNAL_SERVER_ERROR);

        }
        return new ResponseEntity<CategoryResponseRest>(categoryResponse, HttpStatus.OK);
    }
}
