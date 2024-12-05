package com.bakery.BakeryManagement.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bakery.BakeryManagement.model.Products;
import com.bakery.BakeryManagement.model.Promotion;
import com.bakery.BakeryManagement.repository.PromotionRepository;

import java.util.List;

@Service
public class PromotionService {

    @Autowired
    private PromotionRepository promotionRepository;

    public Promotion createPromotion(Promotion promotion) {
        return promotionRepository.save(promotion);
    }

    public List<Promotion> getAllPromotions() {
        return promotionRepository.findAll();
    }

    public void deletePromotion(Long id) {
        promotionRepository.deleteById(id);
    }
    
    public Promotion getPromotionById(Long id) {
        return promotionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Promotion not found with id: " + id));
    }
}