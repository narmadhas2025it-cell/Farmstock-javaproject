package com.farmstock.inventorytracker.service;

import com.farmstock.inventorytracker.dto.*;
import com.farmstock.inventorytracker.entity.Crop;
import com.farmstock.inventorytracker.entity.HarvestBatch;
import com.farmstock.inventorytracker.entity.Sale;
import com.farmstock.inventorytracker.exception.InsufficientStockException;
import com.farmstock.inventorytracker.exception.ResourceNotFoundException;
import com.farmstock.inventorytracker.repository.CropRepository;
import com.farmstock.inventorytracker.repository.HarvestBatchRepository;
import com.farmstock.inventorytracker.repository.SaleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class FarmStockService {

    private final CropRepository cropRepository;
    private final HarvestBatchRepository harvestBatchRepository;
    private final SaleRepository saleRepository;

    public FarmStockService(CropRepository cropRepository,
                            HarvestBatchRepository harvestBatchRepository,
                            SaleRepository saleRepository) {
        this.cropRepository = cropRepository;
        this.harvestBatchRepository = harvestBatchRepository;
        this.saleRepository = saleRepository;
    }

    public Crop addCrop(Crop crop) {
        return cropRepository.save(crop);
    }

    public List<Crop> getAllCrops() {
        return cropRepository.findAll();
    }

    @Transactional
    public HarvestBatch logHarvest(HarvestBatchRequest request) {
        Crop crop = cropRepository.findById(request.getCropId())
                .orElseThrow(() -> new ResourceNotFoundException("Crop not found with id: " + request.getCropId()));

        HarvestBatch batch = new HarvestBatch(crop, request.getQuantity(), request.getHarvestDate());
        return harvestBatchRepository.save(batch);
    }

    @Transactional
    public Sale recordSale(SaleRequest request) {
        Crop crop = cropRepository.findById(request.getCropId())
                .orElseThrow(() -> new ResourceNotFoundException("Crop not found with id: " + request.getCropId()));

        // Business Rule: Available Stock = Sum of Harvested - Sum of Sold
        Double totalHarvested = harvestBatchRepository.sumQuantityByCropId(crop.getId());
        Double totalSold = saleRepository.sumQuantityByCropId(crop.getId());
        Double currentStock = totalHarvested - totalSold;

        // Stock-a vida adhigama sell panna koodathu
        if (request.getQuantity() > currentStock) {
            throw new InsufficientStockException(
                    "Cannot process sale! Requested quantity: " + request.getQuantity()
                            + " exceeds available stock: " + currentStock + " for crop: " + crop.getName()
            );
        }

        Double totalAmount = request.getQuantity() * request.getPricePerUnit();
        Sale sale = new Sale(crop, request.getQuantity(), request.getPricePerUnit(), totalAmount, request.getSaleDate());
        return saleRepository.save(sale);
    }

    public StockResponse getStockByCropId(Long cropId) {
        Crop crop = cropRepository.findById(cropId)
                .orElseThrow(() -> new ResourceNotFoundException("Crop not found with id: " + cropId));

        Double totalHarvested = harvestBatchRepository.sumQuantityByCropId(cropId);
        Double totalSold = saleRepository.sumQuantityByCropId(cropId);
        Double availableStock = totalHarvested - totalSold;

        return new StockResponse(crop.getId(), crop.getName(), totalHarvested, totalSold, availableStock);
    }

    public List<StockResponse> getAllStockLevels() {
        List<Crop> crops = cropRepository.findAll();
        List<StockResponse> responses = new ArrayList<>();
        for (Crop crop : crops) {
            responses.add(getStockByCropId(crop.getId()));
        }
        return responses;
    }

    public RevenueResponse getRevenueByCropAndDateRange(Long cropId, LocalDate startDate, LocalDate endDate) {
        Crop crop = cropRepository.findById(cropId)
                .orElseThrow(() -> new ResourceNotFoundException("Crop not found with id: " + cropId));

        Double totalRevenue = saleRepository.sumRevenueByCropAndDateRange(cropId, startDate, endDate);
        return new RevenueResponse(crop.getId(), crop.getName(), startDate, endDate, totalRevenue);
    }
}