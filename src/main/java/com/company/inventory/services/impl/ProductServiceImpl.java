package com.company.inventory.services.impl;

import com.company.inventory.models.Category;
import com.company.inventory.models.Product;
import com.company.inventory.repositories.ICategoryRepository;
import com.company.inventory.repositories.IProductRepository;
import com.company.inventory.response.ProductResponseRest;
import com.company.inventory.services.IProductService;
import com.company.inventory.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    private IProductRepository productRepository;

    @Autowired
    private ICategoryRepository categoryRepository;

    @Override
    @Transactional
    public ResponseEntity<ProductResponseRest> save(Product product, Long categoryId) {

        ProductResponseRest response = new ProductResponseRest();
        List<Product> products = new ArrayList<>();

        try {

            Optional<Category> category = categoryRepository.findById(categoryId);

            if (category.isPresent()){
                product.setCategory(category.get());
            }else {
                response.setMetadata("Respuesta no ok", "-1", "Categoriá no encontrada asociada al producto");
                return new ResponseEntity<ProductResponseRest>(response, HttpStatus.NOT_FOUND);
            }

            Product productSave = productRepository.save(product);

            if (productSave != null) {
                products.add(productSave);
                response.getProductResponse().setProducts(products);
                response.setMetadata("Respuesta ok", "00", "Producto guardado");
            }else {
                response.setMetadata("Respuesta no ok", "-1", "Producto no guardado");
                return new ResponseEntity<ProductResponseRest>(response, HttpStatus.BAD_REQUEST);
            }

        }catch (Exception e) {
            e.getStackTrace();
            response.setMetadata("Respuesta no ok", "-1", "Error al guardar producto");
            return new ResponseEntity<ProductResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<ProductResponseRest>(response, HttpStatus.OK);
    }

    @Override
    @Transactional (readOnly = true)
    public ResponseEntity<ProductResponseRest> searchById(Long productId) {

        ProductResponseRest response = new ProductResponseRest();
        List<Product> products = new ArrayList<>();

        try {

            Optional<Product> product = productRepository.findById(productId);

            if (product.isPresent()){
                byte[] imagenDescompressed = Util.decompressZLib(product.get().getPicture());
                product.get().setPicture(imagenDescompressed);
                products.add(product.get());
                response.getProductResponse().setProducts(products);
                response.setMetadata("Respuesta ok", "00", "Producto encontrado");
            }else {
                response.setMetadata("Respuesta no ok", "-1", "Producto no encontrado");
                return new ResponseEntity<ProductResponseRest>(response, HttpStatus.NOT_FOUND);
            }

        }catch (Exception e) {
            e.getStackTrace();
            response.setMetadata("Respuesta no ok", "-1", "Error al buscar producto");
            return new ResponseEntity<ProductResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<ProductResponseRest>(response, HttpStatus.OK);
    }

    @Override
    @Transactional (readOnly = true)
    public ResponseEntity<ProductResponseRest> searchByName(String name) {

        ProductResponseRest response = new ProductResponseRest();
        List<Product> products = new ArrayList<>();
        List<Product> listAux = new ArrayList<>();

        try {

            listAux = productRepository.findByNameContainingIgnoreCase(name);

            if (listAux.size() > 0) {

                listAux.stream().forEach( (p) -> {
                    byte[] imagenDescompressed = Util.decompressZLib(p.getPicture());
                    p.setPicture(imagenDescompressed);
                    products.add(p);
                });

                response.getProductResponse().setProducts(products);
                response.setMetadata("Respuesta ok", "00", "Productos encontrado");
            }else {
                response.setMetadata("Respuesta no ok", "-1", "Producto no encontrado");
                return new ResponseEntity<ProductResponseRest>(response, HttpStatus.NOT_FOUND);
            }

        }catch (Exception e) {
            e.getStackTrace();
            response.setMetadata("Respuesta no ok", "-1", "Error al buscar al productos por nombre");
            return new ResponseEntity<ProductResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<ProductResponseRest>(response, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<ProductResponseRest> deleteById(Long productId) {

        ProductResponseRest response = new ProductResponseRest();

        try {

            productRepository.deleteById(productId);
            response.setMetadata("Respuesta ok", "00", "Producto eliminado");

        }catch (Exception e) {
            e.getStackTrace();
            response.setMetadata("Respuesta no ok", "-1", "Error al eliminar producto");
            return new ResponseEntity<ProductResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<ProductResponseRest>(response, HttpStatus.OK);
    }
}
