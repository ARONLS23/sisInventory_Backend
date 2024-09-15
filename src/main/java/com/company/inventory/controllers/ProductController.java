package com.company.inventory.controllers;

import com.company.inventory.models.Product;
import com.company.inventory.response.ProductResponseRest;
import com.company.inventory.services.IProductService;
import com.company.inventory.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1")
public class ProductController {

    @Autowired
    private IProductService productService;

    @PostMapping("/products")
    public ResponseEntity<ProductResponseRest> saveProduct(
            @RequestParam ("picture") MultipartFile picture,
            @RequestParam ("name") String name,
            @RequestParam ("price") double price,
            @RequestParam ("quantity") int quantity,
            @RequestParam ("categoryId") Long categoryId) throws IOException {

        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setQuantity(quantity);
        product.setPicture(Util.compressZLib(picture.getBytes()));

        ResponseEntity<ProductResponseRest> response = productService.save(product, categoryId);

        return response;
    }

    @GetMapping("/products/{productId}")
    public ResponseEntity<ProductResponseRest> searchProductById(@PathVariable Long productId){
        ResponseEntity<ProductResponseRest> response = productService.searchById(productId);
        return response;
    }

    @GetMapping("/products/filter/{name}")
    public ResponseEntity<ProductResponseRest> searchProductByName(@PathVariable String name){
        ResponseEntity<ProductResponseRest> response = productService.searchByName(name);
        return response;
    }

    @DeleteMapping("/products/{productId}")
    public ResponseEntity<ProductResponseRest> deleteProductById(@PathVariable Long productId){
        ResponseEntity<ProductResponseRest> response = productService.deleteById(productId);
        return response;
    }

    @GetMapping("/products")
    public ResponseEntity<ProductResponseRest> searchProducts(){
        ResponseEntity<ProductResponseRest> response = productService.search();
        return response;
    }

    @PutMapping("/products/{productId}")
    public ResponseEntity<ProductResponseRest> updateProduct(
            @RequestParam ("picture") MultipartFile picture,
            @RequestParam ("name") String name,
            @RequestParam ("price") double price,
            @RequestParam ("quantity") int quantity,
            @RequestParam ("categoryId") Long categoryId,
            @PathVariable Long productId) throws IOException {

        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setQuantity(quantity);
        product.setPicture(Util.compressZLib(picture.getBytes()));

        ResponseEntity<ProductResponseRest> response = productService.update(product, categoryId, productId);

        return response;
    }

}
