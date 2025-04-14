package com.hexaware.electronics.dao;

import java.sql.*;
import com.hexaware.electronics.entity.Inventory;
import com.hexaware.electronics.entity.Product;
import com.hexaware.electronics.exceptions.*;

public class InventoryDao implements IInventoryDao {

    Connection conn;

    public InventoryDao() {
        try {
			conn = DBUtil.getDBConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @Override
    public int addInventory(Inventory inventory) {
        try {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO inventory (inventoryID, productID, quantityInStock, lastStockUpdate) VALUES (?, ?, ?, ?)");
            stmt.setInt(1, inventory.getInventoryID());
            stmt.setInt(2, inventory.getProduct().getProductID());
            stmt.setInt(3, inventory.getQuantityInStock());
            stmt.setTimestamp(4, Timestamp.valueOf(inventory.getLastStockUpdate()));
            return stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public Inventory getInventoryByProductId(int productId) throws ProductNotFoundException {
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM inventory WHERE productID = ?");
            stmt.setInt(1, productId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Product product = new Product(0, null, null, productId, false);
                product.setProductID(productId);

                return new Inventory(
                    rs.getInt("inventoryID"),
                    product,
                    rs.getInt("quantityInStock")
                );
            } else {
                throw new ProductNotFoundException("Inventory not found for product ID: " + productId);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int updateInventory(int productId, int quantity) throws InsufficientStockException {
        try {
            PreparedStatement check = conn.prepareStatement("SELECT quantityInStock FROM inventory WHERE productID = ?");
            check.setInt(1, productId);
            ResultSet rs = check.executeQuery();

            if (rs.next()) {
                int available = rs.getInt("quantityInStock");
                if (available < quantity) {
                    throw new InsufficientStockException("Not enough stock for product ID: " + productId);
                }
            }

            PreparedStatement stmt = conn.prepareStatement("UPDATE inventory SET quantityInStock = quantityInStock - ?, lastStockUpdate = ? WHERE productID = ?");
            stmt.setInt(1, quantity);
            stmt.setTimestamp(2, Timestamp.valueOf(java.time.LocalDateTime.now()));
            stmt.setInt(3, productId);

            return stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }
} 
