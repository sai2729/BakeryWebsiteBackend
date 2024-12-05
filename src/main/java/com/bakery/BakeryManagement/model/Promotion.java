package com.bakery.BakeryManagement.model;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "promotions")
public class Promotion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private int discountPercent;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;

    @Column(nullable = false)
    private boolean applyToAll;
    
    @Column(nullable = false)
    private Double minCartValue; // Add this field


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getDiscountPercent() {
		return discountPercent;
	}

	public void setDiscountPercent(int discountPercent) {
		this.discountPercent = discountPercent;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

    public boolean isApplyToAll() { // Custom Getter
        return applyToAll;
    }

    public void setApplyToAll(boolean applyToAll) {
        this.applyToAll = applyToAll;
    }
    
    // Getters and setters
    public Double getMinCartValue() {
        return minCartValue;
    }

    public void setMinCartValue(Double minCartValue) {
        this.minCartValue = minCartValue;
    }
}