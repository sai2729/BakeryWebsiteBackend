package com.bakery.BakeryManagement.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bakery.BakeryManagement.model.Order;
import com.bakery.BakeryManagement.model.OrderItem;
import com.bakery.BakeryManagement.model.OrderRequest;
import com.bakery.BakeryManagement.model.Promotion;
import com.bakery.BakeryManagement.model.User;
import com.bakery.BakeryManagement.service.OrderService;
import com.bakery.BakeryManagement.service.PromotionService;
import com.bakery.BakeryManagement.service.UserService;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;
    
    @Autowired
    private PromotionService promotionService;
    
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Order>> getOrdersByUser(@PathVariable int userId) {
        try {
            List<Order> orders = orderService.getOrdersByUser(userId);
            return ResponseEntity.ok(orders);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    
    @GetMapping("/{orderId}/items")
    public ResponseEntity<List<OrderItem>> getOrderItems(@PathVariable int orderId) {
        try {
            List<OrderItem> orderItems = orderService.getOrderItemsByOrderId(orderId);
            return ResponseEntity.ok(orderItems);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    
    @GetMapping("/admin/orders")
    public ResponseEntity<List<Order>> getAllOrders() {
        try {
            // Fetch orders sorted by status (PENDING first, COMPLETED last)
            List<Order> orders = orderService.getOrdersForStaff();
            return ResponseEntity.ok(orders);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/place")
    public ResponseEntity<Map<String, String>> placeOrder(@RequestBody OrderRequest orderRequest) {
        try {
            // Fetch the user
            User user = userService.findById(orderRequest.getUserId());
            
            // Convert discount from double to BigDecimal
            BigDecimal discount = BigDecimal.valueOf(orderRequest.getDiscount());

//            // Fetch the Promotion object using its ID (if not already provided)
//            Promotion promotion = null;
//            if (orderRequest.getPromotion() != null) {
//                promotion = promotionService.getPromotionById(Long.valueOf(orderRequest.getPromotion()));
//            }

            // Call the placeOrder method with corrected arguments
            orderService.placeOrder(user, orderRequest.getCartItems(), discount, orderRequest.getPromotion());
            // Create a JSON response
            Map<String, String> response = new HashMap<>();
            response.put("message", "Order placed successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
        	Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Failed to place order");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);        }
    }
    
    @PutMapping("/{orderId}/status")
    public ResponseEntity<Map<String, String>> updateOrderStatus(
            @PathVariable int orderId,
            @RequestBody Map<String, String> requestBody) {
        try {
            String newStatus = requestBody.get("status");
            // Logic to update the order status
            orderService.updateOrderStatus(orderId, newStatus);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Order status updated successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Failed to update order status");
            return ResponseEntity.status(500).body(errorResponse);
        }
    }
}