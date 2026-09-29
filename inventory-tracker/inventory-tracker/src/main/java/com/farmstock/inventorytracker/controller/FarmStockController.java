package com.farmstock.inventorytracker.controller;

import com.farmstock.inventorytracker.dto.*;
import com.farmstock.inventorytracker.entity.Crop;
import com.farmstock.inventorytracker.entity.HarvestBatch;
import com.farmstock.inventorytracker.entity.Sale;
import com.farmstock.inventorytracker.service.FarmStockService;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class FarmStockController {

    private final FarmStockService farmStockService;

    public FarmStockController(FarmStockService farmStockService) {
        this.farmStockService = farmStockService;
    }

    // 1. Create a new crop
    @PostMapping("/crops")
    public ResponseEntity<Crop> createCrop(@Valid @RequestBody Crop crop) {
        return new ResponseEntity<>(farmStockService.addCrop(crop), HttpStatus.CREATED);
    }

    // 2. Get all crops (UI dropdown list load panna)
    @GetMapping("/crops")
    public ResponseEntity<List<Crop>> getAllCrops() {
        return ResponseEntity.ok(farmStockService.getAllCrops());
    }

    // 3. Log a harvest batch
    @PostMapping("/harvests")
    public ResponseEntity<HarvestBatch> logHarvest(@Valid @RequestBody HarvestBatchRequest request) {
        return new ResponseEntity<>(farmStockService.logHarvest(request), HttpStatus.CREATED);
    }

    // 4. Record a sale
    @PostMapping("/sales")
    public ResponseEntity<Sale> recordSale(@Valid @RequestBody SaleRequest request) {
        return new ResponseEntity<>(farmStockService.recordSale(request), HttpStatus.CREATED);
    }

    // 5. View current stock levels per crop
    @GetMapping("/stock/{cropId}")
    public ResponseEntity<StockResponse> getStockForCrop(@PathVariable Long cropId) {
        return ResponseEntity.ok(farmStockService.getStockByCropId(cropId));
    }

    // 6. View all crops current stock levels
    @GetMapping("/stock")
    public ResponseEntity<List<StockResponse>> getAllStock() {
        return ResponseEntity.ok(farmStockService.getAllStockLevels());
    }

    // 7. View total revenue per crop over a date range
    @GetMapping("/revenue")
    public ResponseEntity<RevenueResponse> getRevenue(
            @RequestParam Long cropId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return ResponseEntity.ok(farmStockService.getRevenueByCropAndDateRange(cropId, startDate, endDate));
    }
}