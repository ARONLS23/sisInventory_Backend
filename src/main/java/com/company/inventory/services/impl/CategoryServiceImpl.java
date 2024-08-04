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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<CategoryResponseRest> searchById(Long id) {

        CategoryResponseRest categoryResponse = new CategoryResponseRest();
        List<Category> list = new ArrayList<>();

        try{

            Optional<Category> category = categoryRepository.findById(id);

            if(category.isPresent()){
                list.add(category.get());
                categoryResponse.getCategoryResponse().setCategory(list);
                categoryResponse.setMetadata("Respuesta ok", "00", "Categoría encontrada");

            }else {
                categoryResponse.setMetadata("Respuesta no ok", "-1", "Categoría no encontrada");
                return new ResponseEntity<CategoryResponseRest>(categoryResponse, HttpStatus.NOT_FOUND);
            }

        }catch (Exception e){

            categoryResponse.setMetadata("Respuesta no ok", "-1", "Error al consultar por id");
            e.getStackTrace();
            return new ResponseEntity<CategoryResponseRest>(categoryResponse, HttpStatus.INTERNAL_SERVER_ERROR);

        }
        return new ResponseEntity<CategoryResponseRest>(categoryResponse, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<CategoryResponseRest> save(Category category) {

        CategoryResponseRest categoryResponse = new CategoryResponseRest();
        List<Category> list = new ArrayList<>();

        try{

            Category categorySaved = categoryRepository.save(category);

            if(categorySaved != null){
                list.add(categorySaved);
                categoryResponse.getCategoryResponse().setCategory(list);
                categoryResponse.setMetadata("Respuesta ok", "00", "Categoría registrada en la BD");

            }else {
                categoryResponse.setMetadata("Respuesta no ok", "-1", "Categoría no registrada en la BD");
                return new ResponseEntity<CategoryResponseRest>(categoryResponse, HttpStatus.BAD_REQUEST);
            }

        }catch (Exception e){

            categoryResponse.setMetadata("Respuesta no ok", "-1", "Error al guardar categoría");
            e.getStackTrace();
            return new ResponseEntity<CategoryResponseRest>(categoryResponse, HttpStatus.INTERNAL_SERVER_ERROR);

        }
        return new ResponseEntity<CategoryResponseRest>(categoryResponse, HttpStatus.OK);
    }
}
