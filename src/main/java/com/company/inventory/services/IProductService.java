package com.company.inventory.services;

import com.company.inventory.models.Product;
import com.company.inventory.response.ProductResponseRest;
import org.springframework.http.ResponseEntity;

public interface IProductService {

    public ResponseEntity<ProductResponseRest> save (Product product, Long categoryId);

    public ResponseEntity<ProductResponseRest> searchById (Long productId);

    public ResponseEntity<ProductResponseRest> searchByName (String name);

    public ResponseEntity<ProductResponseRest> deleteById (Long productId);

    public ResponseEntity<ProductResponseRest> search ();

    public ResponseEntity<ProductResponseRest> update (Product product, Long categoryId, Long productId);

}
