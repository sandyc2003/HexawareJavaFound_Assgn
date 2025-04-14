package com.hexaware.electronics.dao;

import com.hexaware.electronics.entity.Inventory;
import com.hexaware.electronics.exceptions.*;

public interface IInventoryDao {

    int addInventory(Inventory inventory);

    Inventory getInventoryByProductId(int productId) throws ProductNotFoundException;

    int updateInventory(int productId, int quantity) throws InsufficientStockException;
} 
