package com.company.inventory.repositories;

import com.company.inventory.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findByNameContainingIgnoreCase(String name);

}
