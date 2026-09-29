package com.farmstock.inventorytracker.repository;

import com.farmstock.inventorytracker.entity.Crop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CropRepository extends JpaRepository<Crop, Long> {
}