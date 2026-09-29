package com.farmstock.inventorytracker.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.time.LocalDate;

@Entity
@Table(name = "sales")
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "crop_id", nullable = false)
    @NotNull(message = "Crop reference is mandatory")
    private Crop crop;

    @NotNull(message = "Quantity sold is mandatory")
    @Positive(message = "Quantity must be greater than zero")
    @Column(nullable = false)
    private Double quantity;

    @NotNull(message = "Price per unit is mandatory")
    @Positive(message = "Price per unit must be greater than zero")
    @Column(nullable = false)
    private Double pricePerUnit;

    @Column(nullable = false)
    private Double totalAmount;

    @NotNull(message = "Sale date is mandatory")
    @Column(nullable = false)
    private LocalDate saleDate;

    public Sale() {}

    public Sale(Crop crop, Double quantity, Double pricePerUnit, Double totalAmount, LocalDate saleDate) {
        this.crop = crop;
        this.quantity = quantity;
        this.pricePerUnit = pricePerUnit;
        this.totalAmount = totalAmount;
        this.saleDate = saleDate;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Crop getCrop() { return crop; }
    public void setCrop(Crop crop) { this.crop = crop; }

    public Double getQuantity() { return quantity; }
    public void setQuantity(Double quantity) { this.quantity = quantity; }

    public Double getPricePerUnit() { return pricePerUnit; }
    public void setPricePerUnit(Double pricePerUnit) { this.pricePerUnit = pricePerUnit; }

    public Double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(Double totalAmount) { this.totalAmount = totalAmount; }

    public LocalDate getSaleDate() { return saleDate; }
    public void setSaleDate(LocalDate saleDate) { this.saleDate = saleDate; }
}