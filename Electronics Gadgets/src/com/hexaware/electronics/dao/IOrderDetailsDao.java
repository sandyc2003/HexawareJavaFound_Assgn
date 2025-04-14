package com.hexaware.electronics.dao;

import java.util.List;
import com.hexaware.electronics.entity.OrderDetails;
import com.hexaware.electronics.exceptions.IncompleteOrderException;
import com.hexaware.electronics.exceptions.InsufficientStockException;

public interface IOrderDetailsDao {

    int addOrderDetail(OrderDetails detail) throws IncompleteOrderException, InsufficientStockException;

    List<OrderDetails> getOrderDetailsByOrderId(int orderId);
} 
