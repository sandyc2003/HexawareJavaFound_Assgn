package com.hexaware.electronics.dao;

import java.sql.*;
import java.util.*;

import com.hexaware.electronics.entity.*;
import com.hexaware.electronics.exceptions.*;

public class OrderDao implements IOrderDao {

    Connection conn;

    public OrderDao() {
        try {
			conn = DBUtil.getDBConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @Override
    public int placeOrder(Order order) throws InsufficientStockException, IncompleteOrderException {
        if (order.getCustomer() == null || order.getProductList() == null || order.getProductList().isEmpty()) {
            throw new IncompleteOrderException("Order must have customer and products");
        }

        try {
            PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO orders (orderID, customerID, orderDate, totalAmount, status) VALUES (?, ?, ?, ?, ?)"
            );
            stmt.setInt(1, order.getOrderID());
            stmt.setInt(2, order.getCustomer().getCustomerID());
            stmt.setTimestamp(3, Timestamp.valueOf(order.getOrderDate()));
            stmt.setDouble(4, order.getTotalAmount());
            stmt.setString(5, order.getStatus());

            return stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int cancelOrder(int orderId) throws OrderNotFoundException, ConcurrencyException {
        try {
            PreparedStatement stmt = conn.prepareStatement("UPDATE orders SET status = 'Cancelled' WHERE orderID = ?");
            stmt.setInt(1, orderId);
            int rows = stmt.executeUpdate();

            if (rows == 0) {
                throw new OrderNotFoundException("Order not found with ID: " + orderId);
            }
            return rows;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public Order getOrderById(int orderId) throws OrderNotFoundException {
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM orders WHERE orderID = ?");
            stmt.setInt(1, orderId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Order order = new Order();
                order.setOrderID(rs.getInt("orderID"));
                order.setOrderDate(rs.getTimestamp("orderDate").toLocalDateTime());
                order.setTotalAmount(rs.getDouble("totalAmount"));
                order.setStatus(rs.getString("status"));
                // You can fetch customer/product separately and set if needed
                return order;
            } else {
                throw new OrderNotFoundException("Order not found with ID: " + orderId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Order> getAllOrders() {
        List<Order> list = new ArrayList<>();
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM orders");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Order order = new Order();
                order.setOrderID(rs.getInt("orderID"));
                order.setOrderDate(rs.getTimestamp("orderDate").toLocalDateTime());
                order.setTotalAmount(rs.getDouble("totalAmount"));
                order.setStatus(rs.getString("status"));
                list.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
