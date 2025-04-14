package com.hexaware.electronics.services;

import java.util.List;
import com.hexaware.electronics.entity.*;
import com.hexaware.electronics.exceptions.*;

public interface IElectronicsService {

	 // Product operations
    int addProduct(Product p) throws  InvalidDataException;
    int updateProduct(Product p) throws ProductNotFoundException, InvalidDataException;
    Product getProductById(int pid) throws ProductNotFoundException;
    List<Product> getAllProducts();

    // Customer operations
    int addCustomer(Customer c) throws InvalidDataException;
    Customer getCustomerById(int cid) throws CustomerNotFoundException;

    // Order operations
    int placeOrder(Order o) throws InsufficientStockException, IncompleteOrderException;
    Order getOrderById(int oid) throws OrderNotFoundException;
    List<Order> getAllOrders();
    int cancelOrder(int oid) throws OrderNotFoundException, ConcurrencyException;

    // Inventory operations
    int addInventory(Inventory inventory);
    Inventory getInventoryByProductId(int pid) throws ProductNotFoundException;
    int updateInventory(int pid, int quantity) throws InsufficientStockException;

    // Order Detail operations
    int addOrderDetail(OrderDetails detail) throws InsufficientStockException, IncompleteOrderException;
	int processPayment(int orderId, Payment payment) throws PaymentFailedException;

 
} 