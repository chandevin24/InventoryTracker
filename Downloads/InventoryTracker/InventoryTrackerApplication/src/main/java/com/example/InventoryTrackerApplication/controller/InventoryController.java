package com.example.InventoryTrackerApplication.controller;

import com.example.InventoryTrackerApplication.model.Inventory;
import com.example.InventoryTrackerApplication.repo.InventoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class InventoryController {


    @Autowired
    InventoryRepo inventoryRepository;

    @GetMapping("/getAllInventory")
    public ResponseEntity<List<Inventory>> getAllInventory() {
        try {
            List<Inventory> inventoryList = new ArrayList<>();
            inventoryRepository.findAll().forEach(inventoryList::add);

            if (inventoryList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(inventoryList, HttpStatus.OK);
        } catch(Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getItem/{id}")
    public ResponseEntity<Inventory> getItem(@PathVariable Long id) {
        Optional<Inventory> item = inventoryRepository.findById(id);
        if (item.isPresent()) {
            return new ResponseEntity<>(item.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/addItem")
    public ResponseEntity<Inventory> addItem(@RequestBody Inventory inventory) {
        try {
            Inventory inventoryObj = inventoryRepository.save(inventory);
            return new ResponseEntity<>(inventoryObj, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/updateItem/{id}")
    public ResponseEntity<Inventory> updateItem(@PathVariable Long id, @RequestBody Inventory inventory) {
        try {
            Optional<Inventory> invData = inventoryRepository.findById(id);
            if (invData.isPresent()) {
                Inventory updatedInventoryData = invData.get();
                updatedInventoryData.setName(inventory.getName());
                updatedInventoryData.setPrice(inventory.getPrice());

                Inventory inventoryObj = inventoryRepository.save(updatedInventoryData);
                return new ResponseEntity<>(inventoryObj, HttpStatus.CREATED);
            }

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteItem/{id}")
    public ResponseEntity<HttpStatus> deleteItem(@PathVariable Long id) {
        try {
            inventoryRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/deleteAllInventory")
    public ResponseEntity<HttpStatus> deleteAllInventory() {
        try {
            inventoryRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}