package com.bakery.BakeryManagement.repository;



import com.example.promotion.entity.Promotion;
import com.example.auth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PromotionRepository extends JpaRepository<Promotion, Long> {

    // Find all promotions created by a specific user
    List<Promotion> findByCreatedBy(User user);

    // Find all promotions within a date range
    List<Promotion> findByStartDateBetweenAndEndDateBetween(
            LocalDateTime startDate, LocalDateTime endDate, 
            LocalDateTime endStart, LocalDateTime endEnd);
}


