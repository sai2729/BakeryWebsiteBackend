package com.bakery.BakeryManagement.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.bakery.BakeryManagement.model.CartItem;
import com.bakery.BakeryManagement.model.Order;
import com.bakery.BakeryManagement.model.OrderItem;
import com.bakery.BakeryManagement.model.OrderStatus;
import com.bakery.BakeryManagement.model.Products;
import com.bakery.BakeryManagement.model.Promotion;
import com.bakery.BakeryManagement.model.User;
import com.bakery.BakeryManagement.repository.OrderItemRepository;
import com.bakery.BakeryManagement.repository.OrderRepository;
import com.bakery.BakeryManagement.repository.ProductRepository;

import jakarta.transaction.Transactional;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private ProductRepository productRepository;
    


    public List<Order> getOrdersByUser(int userId) {
        return orderRepository.findByUserUserId(userId);
    }


    public List<OrderItem> getOrderItemsByOrderId(int orderId) {
        return orderItemRepository.findByOrderOrderId(orderId);
    }
    
    public List<Order> getOrdersForStaff() {
        return orderRepository.findAll(Sort.by("status").ascending());
    }
    
    public void updateOrderStatus(int orderId, String status) {
        Order order = orderRepository.findById((long) orderId).orElseThrow(() -> new IllegalArgumentException("Order not found"));
        order.setStatus(OrderStatus.valueOf(status));
        orderRepository.save(order);
    }

    @Transactional
    public void placeOrder(User user, List<CartItem> cartItems, BigDecimal discount, Promotion promotion) {
        BigDecimal subtotal = cartItems.stream()
            .map(item -> BigDecimal.valueOf(item.getPrice())
                    .multiply(BigDecimal.valueOf(item.getQuantity())))
            .reduce(BigDecimal.ZERO, BigDecimal::add);

        System.out.println("Subtotal: " + subtotal);

        BigDecimal totalAmount = subtotal.subtract(subtotal.multiply(discount.divide(new BigDecimal(100))));

        // Create Order
        Order order = new Order();
        order.setUser(user);
        order.setCreatedAt(LocalDateTime.now());
        order.setTotalAmount(totalAmount);
        order.setStatus(OrderStatus.PENDING);
        order.setPromotion(promotion);
        Order savedOrder = orderRepository.save(order);

        // Create Order Items
        for (CartItem cartItem : cartItems) {
            // Fetch the product and handle missing product case
            Products product = productRepository.findById(Long.valueOf(cartItem.getId()))
                    .orElseThrow(() -> new IllegalArgumentException("Product not found for ID: " + cartItem.getId()));

            // Create and save the order item
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(savedOrder);
            orderItem.setProduct(product); // Now correctly set the product
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setPrice(cartItem.getPrice());
            orderItemRepository.save(orderItem);
        }
    }
}