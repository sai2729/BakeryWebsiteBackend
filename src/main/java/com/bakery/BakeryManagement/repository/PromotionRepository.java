package com.bakery.BakeryManagement.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.bakery.BakeryManagement.model.Promotion;

public interface PromotionRepository extends JpaRepository<Promotion, Long> {
}
