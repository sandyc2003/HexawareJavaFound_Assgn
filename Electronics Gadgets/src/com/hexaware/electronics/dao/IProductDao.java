package com.hexaware.electronics.dao;

import java.util.List;
import com.hexaware.electronics.entity.Product;
import com.hexaware.electronics.exceptions.ProductNotFoundException;
import com.hexaware.electronics.exceptions.InvalidDataException;

public interface IProductDao {

    int addProduct(Product product) throws InvalidDataException;

    int updateProduct(Product product) throws ProductNotFoundException, InvalidDataException;

    int deleteProduct(int productId) throws ProductNotFoundException;

    Product getProductById(int productId) throws ProductNotFoundException;

    List<Product> getAllProducts();
} 
