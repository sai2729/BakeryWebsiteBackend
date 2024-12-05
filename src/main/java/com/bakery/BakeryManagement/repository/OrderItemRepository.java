package com.bakery.BakeryManagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bakery.BakeryManagement.model.Order;
import com.bakery.BakeryManagement.model.OrderItem;
import com.bakery.BakeryManagement.model.Products;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
	// Query to fetch all items for a specific order
    List<OrderItem> findByOrderOrderId(int orderId);
}
