package com.farmstock.inventorytracker.dto;

import java.time.LocalDate;

public class RevenueResponse {
    private Long cropId;
    private String cropName;
    private LocalDate startDate;
    private LocalDate endDate;
    private Double totalRevenue;

    public RevenueResponse(Long cropId, String cropName, LocalDate startDate, LocalDate endDate, Double totalRevenue) {
        this.cropId = cropId;
        this.cropName = cropName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalRevenue = totalRevenue;
    }

    public Long getCropId() { return cropId; }
    public String getCropName() { return cropName; }
    public LocalDate getStartDate() { return startDate; }
    public LocalDate getEndDate() { return endDate; }
    public Double getTotalRevenue() { return totalRevenue; }
}