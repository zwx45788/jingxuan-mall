package com.example.shopping.repository;

import com.example.shopping.model.ProductSpu;
import com.example.shopping.model.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShopRepository extends JpaRepository<Shop, Long> {
    Optional<Shop> findByMerchantIdAndShopName(Long merchantId, String shopName);
}


