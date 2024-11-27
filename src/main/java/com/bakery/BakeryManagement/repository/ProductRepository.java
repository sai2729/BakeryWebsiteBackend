package com.bakery.BakeryManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bakery.BakeryManagement.model.Products;

public interface ProductRepository extends JpaRepository<Products, Long> {
}