package com.farmstock.inventorytracker.repository;

import com.farmstock.inventorytracker.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {
    @Query("SELECT COALESCE(SUM(s.quantity), 0.0) FROM Sale s WHERE s.crop.id = :cropId")
    Double sumQuantityByCropId(@Param("cropId") Long cropId);

    @Query("SELECT COALESCE(SUM(s.totalAmount), 0.0) FROM Sale s WHERE s.crop.id = :cropId AND s.saleDate BETWEEN :startDate AND :endDate")
    Double sumRevenueByCropAndDateRange(@Param("cropId") Long cropId, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
}