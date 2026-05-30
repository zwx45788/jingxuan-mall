package com.example.shopping.repository;

import com.example.shopping.model.ProductSpu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductSpuRepository extends JpaRepository<ProductSpu, Long> {

}
