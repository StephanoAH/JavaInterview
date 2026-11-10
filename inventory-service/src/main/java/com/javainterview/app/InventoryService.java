package com.javainterview.app;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

public class InventoryService {

    private final Map<String, Integer> stock;

    public InventoryService(Map<String, Integer> initialStock) {
        this.stock = new ConcurrentHashMap<>(initialStock);
    }

    public boolean decreaseStock(String productId, int quantity) {
        AtomicBoolean result = new AtomicBoolean(false);
        if (productId == null || productId.trim().isEmpty()) {
            return result.get();
        }
        stock.computeIfPresent(productId, (key, value) -> {
            if(quantity <= 0 || value < quantity) {
                return value;
            }
            result.set(true);
            return value - quantity;
        });
        return result.get();
    }

    public int getStock(String productId) {
        return stock.getOrDefault(productId, 0);
    }
}