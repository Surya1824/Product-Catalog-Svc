package com.surya.product.catalog.svc.repository;

import com.surya.product.catalog.svc.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query("SELECT p FROM Product p JOIN p.subCategory sc WHERE "
            + "(:query IS NOT NULL AND ("
            + "LOWER(p.name) LIKE LOWER(CONCAT('%', :query, '%')) "
            + "OR LOWER(p.brand) LIKE LOWER(CONCAT('%', :query, '%')) "
            + "OR LOWER(p.category) LIKE LOWER(CONCAT('%', :query, '%')) "
            + "OR LOWER(sc.name) LIKE LOWER(CONCAT('%', :query, '%'))))")
    List<Product> searchProducts(@Param("query") String query);
    

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Product WHERE productId = :productId")
    int deleteByProductId(@Param("productId") Long productId);

}
