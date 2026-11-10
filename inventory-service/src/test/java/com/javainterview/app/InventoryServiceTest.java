package com.javainterview.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * Unit test for simple App.
 */
public class InventoryServiceTest {

   @ParameterizedTest
   @CsvSource({
    "product1, 10, 3, true, 7",
    "product1, 10, 10, true, 0",
    "product1, 10, 15, false, 10",
    "product1, 0, 1, false, 0",
    "product1, 10, 0, false, 10",
    "product1, 10, -5, false, 10",
   })
   @DisplayName("Decrease stock only happens if there is enough stock")
   public void shouldDecreaseStockOnlyIfEnoughStock(String productId, int initialStock, int quantity, boolean expectedResult, int expectedStock) {
    Map<String, Integer> stock = Map.of(productId, initialStock);
    InventoryService inventoryService = new InventoryService(stock);
    boolean result = inventoryService.decreaseStock(productId, quantity);
    assertEquals(expectedResult, result);
    assertEquals(expectedStock, inventoryService.getStock(productId));
   }

   @Test
   @DisplayName("Decrease a product that does not exist")
   public void shouldNotDecreaseStockIfProductDoesNotExist() {
    Map<String, Integer> stock = Map.of("product1", 10);
    InventoryService inventoryService = new InventoryService(stock);
    boolean result = inventoryService.decreaseStock("product2", 1);
    assertFalse(result);
    assertEquals(10, inventoryService.getStock("product1"));
   }

   @ParameterizedTest
   @CsvSource({
    "null",
    "''",
    "' '"
   })
   @DisplayName("Product identifier cannot be null or empty")
   public void shouldNotDecreaseStockIfProductIdentifierIsNullOrEmpty(String productId) {
    Map<String, Integer> stock = Map.of("product1", 10);
    InventoryService inventoryService = new InventoryService(stock);
    boolean result = inventoryService.decreaseStock(productId, 1);
    assertFalse(result);
    assertEquals(10, inventoryService.getStock("product1"));
   }
}
