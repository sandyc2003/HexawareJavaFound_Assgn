package com.hexaware.electronics.dao;

import java.sql.*;
import java.util.*;

import com.hexaware.electronics.entity.OrderDetails;
import com.hexaware.electronics.exceptions.*;

public class OrderDetailsDao implements IOrderDetailsDao {

    Connection conn;

    public OrderDetailsDao() {
        try {
			conn = DBUtil.getDBConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @Override
    public int addOrderDetail(OrderDetails detail) throws IncompleteOrderException, InsufficientStockException {
        if (detail.getOrder() == null || detail.getProduct() == null) {
            throw new IncompleteOrderException("Order or Product is missing");
        }

        try {
            PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO order_details (orderDetailID, orderID, productID, quantity, discount) VALUES (?, ?, ?, ?, ?)"
            );
            stmt.setInt(1, detail.getOrderDetailID());
            stmt.setInt(2, detail.getOrder().getOrderID());
            stmt.setInt(3, detail.getProduct().getProductID());
            stmt.setInt(4, detail.getQuantity());
            stmt.setDouble(5, detail.getDiscount());

            return stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public List<OrderDetails> getOrderDetailsByOrderId(int orderId) {
        List<OrderDetails> list = new ArrayList<>();
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM order_details WHERE orderID = ?");
            stmt.setInt(1, orderId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                OrderDetails detail = new OrderDetails();
                detail.setOrderDetailID(rs.getInt("orderDetailID"));
                detail.setQuantity(rs.getInt("quantity"));
                list.add(detail);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
} 
