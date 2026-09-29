package com.farmstock.inventorytracker.dto;

public class StockResponse {
    private Long cropId;
    private String cropName;
    private Double totalHarvested;
    private Double totalSold;
    private Double availableStock;

    public StockResponse(Long cropId, String cropName, Double totalHarvested, Double totalSold, Double availableStock) {
        this.cropId = cropId;
        this.cropName = cropName;
        this.totalHarvested = totalHarvested;
        this.totalSold = totalSold;
        this.availableStock = availableStock;
    }

    public Long getCropId() { return cropId; }
    public String getCropName() { return cropName; }
    public Double getTotalHarvested() { return totalHarvested; }
    public Double getTotalSold() { return totalSold; }
    public Double getAvailableStock() { return availableStock; }
}