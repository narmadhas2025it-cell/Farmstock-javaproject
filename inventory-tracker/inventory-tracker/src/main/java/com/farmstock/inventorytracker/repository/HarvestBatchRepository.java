package com.farmstock.inventorytracker.repository;

import com.farmstock.inventorytracker.entity.HarvestBatch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface HarvestBatchRepository extends JpaRepository<HarvestBatch, Long> {
    @Query("SELECT COALESCE(SUM(h.quantity), 0.0) FROM HarvestBatch h WHERE h.crop.id = :cropId")
    Double sumQuantityByCropId(@Param("cropId") Long cropId);
}