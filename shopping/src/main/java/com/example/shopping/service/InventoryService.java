package com.example.shopping.service;

import com.example.shopping.repository.ProductSpuRepository;
import org.aspectj.apache.bcel.Repository;
import org.springframework.beans.factory.annotation.Autowired;

public class InventoryService {
    @Autowired
    private ProductSpuRepository productSpuRepository;
}
