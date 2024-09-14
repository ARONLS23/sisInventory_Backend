package com.company.inventory.repositories;

import com.company.inventory.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductRepository extends JpaRepository<Product, Long> {

    @Query ("select p from Product p where p.name like %?1%")
    List<Product> findByNameLike(String name);

    List<Product> findByNameContainingIgnoreCase(String name);

}
