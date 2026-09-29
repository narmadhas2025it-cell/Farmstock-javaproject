package com.farmstock.inventorytracker.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.time.LocalDate;

@Entity
@Table(name = "harvest_batches")
public class HarvestBatch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "crop_id", nullable = false)
    @NotNull(message = "Crop reference is mandatory")
    private Crop crop;

    @NotNull(message = "Quantity is mandatory")
    @Positive(message = "Quantity must be greater than zero")
    @Column(nullable = false)
    private Double quantity;

    @NotNull(message = "Harvest date is mandatory")
    @Column(nullable = false)
    private LocalDate harvestDate;

    public HarvestBatch() {}

    public HarvestBatch(Crop crop, Double quantity, LocalDate harvestDate) {
        this.crop = crop;
        this.quantity = quantity;
        this.harvestDate = harvestDate;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Crop getCrop() { return crop; }
    public void setCrop(Crop crop) { this.crop = crop; }

    public Double getQuantity() { return quantity; }
    public void setQuantity(Double quantity) { this.quantity = quantity; }

    public LocalDate getHarvestDate() { return harvestDate; }
    public void setHarvestDate(LocalDate harvestDate) { this.harvestDate = harvestDate; }
}