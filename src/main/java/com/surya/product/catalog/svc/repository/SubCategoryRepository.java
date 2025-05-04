package com.surya.product.catalog.svc.repository;

import com.surya.product.catalog.svc.model.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubCategoryRepository extends JpaRepository<SubCategory, Integer> {
    public Optional<SubCategory> findByName(String name);
}
