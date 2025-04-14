package com.hexaware.electronics.dao;

import java.sql.*;
import java.util.*;

import com.hexaware.electronics.entity.Product;
import com.hexaware.electronics.exceptions.ProductNotFoundException;
import com.hexaware.electronics.exceptions.DuplicateProductException;
import com.hexaware.electronics.exceptions.InvalidDataException;

public class ProductDao implements IProductDao {

    Connection conn;

    public ProductDao() {
        try {
        	this.conn = DBUtil.getDBConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @Override
    public int addProduct(Product product) throws InvalidDataException {
        if (product.getProductName() == null || product.getProductName().isEmpty()) {
            throw new InvalidDataException("Product name cannot be empty");
        }

        try {
            PreparedStatement checkStmt = conn.prepareStatement("SELECT * FROM products WHERE productID = ?");
            checkStmt.setInt(1, product.getProductID());
            ResultSet rs = checkStmt.executeQuery();
            if (rs.next()) {
                throw new DuplicateProductException("Product with ID already exists.");
            }

            String sql = "INSERT INTO products (productID, productName, description, price, inStock) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, product.getProductID());
            stmt.setString(2, product.getProductName());
            stmt.setString(3, product.getDescription());
            stmt.setDouble(4, product.getPrice());
            stmt.setBoolean(5,product.isInStock());

            return stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        } catch (DuplicateProductException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
    }

    @Override
    public int updateProduct(Product product) throws ProductNotFoundException, InvalidDataException {
        if (product.getProductName() == null || product.getProductName().isEmpty()) {
            throw new InvalidDataException("Product name cannot be empty");
        }

        try {
            PreparedStatement stmt = conn.prepareStatement("UPDATE products SET productName=?, description=?, price=? WHERE productID=?");
            stmt.setString(1, product.getProductName());
            stmt.setString(2, product.getDescription());
            stmt.setDouble(3, product.getPrice());
            stmt.setInt(4, product.getProductID());

            int rows = stmt.executeUpdate();
            if (rows == 0) {
                throw new ProductNotFoundException("Product not found with ID: " + product.getProductID());
            }
            return rows;

        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int deleteProduct(int productId) throws ProductNotFoundException {
        try {
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM products WHERE productID = ?");
            stmt.setInt(1, productId);
            int rows = stmt.executeUpdate();
            if (rows == 0) {
                throw new ProductNotFoundException("Product not found to delete with ID: " + productId);
            }
            return rows;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public Product getProductById(int productId) throws ProductNotFoundException {
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM products WHERE productID = ?");
            stmt.setInt(1, productId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Product(
                    rs.getInt("productID"),
                    rs.getString("productName"),
                    rs.getString("description"),
                    rs.getDouble("price"), rs.getBoolean("inStock")
                );
            } else {
                throw new ProductNotFoundException("Product not found with ID: " + productId);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> productList = new ArrayList<>();
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM products");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Product p = new Product(
                    rs.getInt("productID"),
                    rs.getString("productName"),
                    rs.getString("description"),
                    rs.getDouble("price"), rs.getBoolean("inStock")
                );
                productList.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productList;
    }
} 
