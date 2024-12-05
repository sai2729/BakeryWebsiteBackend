package com.bakery.BakeryManagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bakery.BakeryManagement.model.Order;
import com.bakery.BakeryManagement.model.Products;

public interface OrderRepository extends JpaRepository<Order, Long> {
	List<Order> findByUserUserId(int userId);
}
