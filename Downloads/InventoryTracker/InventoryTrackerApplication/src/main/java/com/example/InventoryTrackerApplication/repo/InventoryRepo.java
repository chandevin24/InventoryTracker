package com.example.InventoryTrackerApplication.repo;

import com.example.InventoryTrackerApplication.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepo extends JpaRepository<Inventory, Long> {

}