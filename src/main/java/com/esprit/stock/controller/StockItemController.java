package com.esprit.stock.controller;

import com.esprit.stock.entity.StockItem;
import com.esprit.stock.service.StockItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("/stock")
public class StockItemController {

    @Autowired
    private StockItemService stockItemService;

    // Updated path to match the Angular service URL
    @GetMapping("/items")
    public List<StockItem> getAllStockItems() {
        return stockItemService.getAllStockItems();
    }

    @GetMapping("/items/{id}")
    public ResponseEntity<StockItem> getStockItemById(@PathVariable(value = "id") Long id) {
        StockItem stockItem = stockItemService.getStockItemById(id)
                .orElseThrow(() -> new RuntimeException("Stock item not found for this id :: " + id));
        return ResponseEntity.ok().body(stockItem);
    }

    @PostMapping("/items")
    public StockItem createStockItem(@RequestBody StockItem stockItem) {
        return stockItemService.addStockItem(stockItem);
    }

    @PutMapping("/items/{id}")
    public ResponseEntity<StockItem> updateStockItem(@PathVariable(value = "id") Long id,
                                                     @RequestBody StockItem stockItemDetails) {
        StockItem updatedStockItem = stockItemService.updateStockItem(id, stockItemDetails);
        return ResponseEntity.ok(updatedStockItem);
    }

    @DeleteMapping("/items/{id}")
    public ResponseEntity<Void> deleteStockItem(@PathVariable(value = "id") Long id) {
        stockItemService.deleteStockItem(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/check-stock")
    public ResponseEntity<Void> checkStockLevels() {
        stockItemService.checkStockLevels();
        return ResponseEntity.noContent().build();
    }
}
