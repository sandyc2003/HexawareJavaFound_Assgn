package com.hexaware.electronics.dao;

import java.util.List;
import com.hexaware.electronics.entity.Order;
import com.hexaware.electronics.exceptions.*;

public interface IOrderDao {

    int placeOrder(Order order) throws InsufficientStockException, IncompleteOrderException;

    int cancelOrder(int orderId) throws OrderNotFoundException, ConcurrencyException;

    Order getOrderById(int orderId) throws OrderNotFoundException;

    List<Order> getAllOrders();
} 
