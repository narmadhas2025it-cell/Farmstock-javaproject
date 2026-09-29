package com.farmstock.inventorytracker.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.time.LocalDate;

public class HarvestBatchRequest {
    @NotNull(message = "cropId is required")
    private Long cropId;

    @NotNull(message = "quantity is required")
    @Positive(message = "quantity must be greater than zero")
    private Double quantity;

    @NotNull(message = "harvestDate is required")
    private LocalDate harvestDate;

    public Long getCropId() { return cropId; }
    public void setCropId(Long cropId) { this.cropId = cropId; }

    public Double getQuantity() { return quantity; }
    public void setQuantity(Double quantity) { this.quantity = quantity; }

    public LocalDate getHarvestDate() { return harvestDate; }
    public void setHarvestDate(LocalDate harvestDate) { this.harvestDate = harvestDate; }
}