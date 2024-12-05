package com.bakery.BakeryManagement.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bakery.BakeryManagement.model.Promotion;
import com.bakery.BakeryManagement.service.PromotionService;

import java.util.List;

@RestController
@RequestMapping("/api/promotions")
@CrossOrigin
public class PromotionController {

    @Autowired
    private PromotionService promotionService;

    @PostMapping
    public ResponseEntity<Promotion> createPromotion(@RequestBody Promotion promotion) {
        if (promotion.isApplyToAll() && promotion.getDiscountPercent() > 0) {
            Promotion newPromotion = promotionService.createPromotion(promotion);
            return ResponseEntity.ok(newPromotion);
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping
    public ResponseEntity<List<Promotion>> getAllPromotions() {
        return ResponseEntity.ok(promotionService.getAllPromotions());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePromotion(@PathVariable Long id) {
        promotionService.deletePromotion(id);
        return ResponseEntity.noContent().build();
    }
}