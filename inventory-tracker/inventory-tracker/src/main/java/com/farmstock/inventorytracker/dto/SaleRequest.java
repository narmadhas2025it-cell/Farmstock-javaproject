package com.farmstock.inventorytracker.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.time.LocalDate;

public class SaleRequest {
    @NotNull(message = "cropId is required")
    private Long cropId;

    @NotNull(message = "quantity is required")
    @Positive(message = "quantity must be greater than zero")
    private Double quantity;

    @NotNull(message = "pricePerUnit is required")
    @Positive(message = "pricePerUnit must be positive")
    private Double pricePerUnit;

    @NotNull(message = "saleDate is required")
    private LocalDate saleDate;

    public Long getCropId() { return cropId; }
    public void setCropId(Long cropId) { this.cropId = cropId; }

    public Double getQuantity() { return quantity; }
    public void setQuantity(Double quantity) { this.quantity = quantity; }

    public Double getPricePerUnit() { return pricePerUnit; }
    public void setPricePerUnit(Double pricePerUnit) { this.pricePerUnit = pricePerUnit; }

    public LocalDate getSaleDate() { return saleDate; }
    public void setSaleDate(LocalDate saleDate) { this.saleDate = saleDate; }
}