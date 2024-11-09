package com.esprit.stock.service;

import com.esprit.stock.entity.StockItem;
import com.esprit.stock.repository.StockItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StockItemService {

    @Autowired
    private StockItemRepository stockItemRepository;

    public List<StockItem> getAllStockItems() {
        return stockItemRepository.findAll();
    }

    public Optional<StockItem> getStockItemById(Long id) {
        return stockItemRepository.findById(id);
    }

    public StockItem addStockItem(StockItem stockItem) {
        return stockItemRepository.save(stockItem);
    }

    public StockItem updateStockItem(Long id, StockItem stockItemDetails) {
        StockItem stockItem = stockItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Stock item not found for this id :: " + id));

        stockItem.setName(stockItemDetails.getName());
        stockItem.setDescription(stockItemDetails.getDescription());
        stockItem.setQuantity(stockItemDetails.getQuantity());
        stockItem.setPrice(stockItemDetails.getPrice());

        return stockItemRepository.save(stockItem);
    }

    public void deleteStockItem(Long id) {
        stockItemRepository.deleteById(id);
    }

    public void checkStockLevels() {
        List<StockItem> items = stockItemRepository.findAll();
        for (StockItem item : items) {
            if (item.getQuantity() < 10) {
                System.out.println("Attention : Le stock de " + item.getName() + " est bas.");
            }
        }
    }
}
