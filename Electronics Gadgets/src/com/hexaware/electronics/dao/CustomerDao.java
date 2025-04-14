package com.hexaware.electronics.dao;

import java.sql.*;
import java.util.*;

import com.hexaware.electronics.entity.Customer;
import com.hexaware.electronics.exceptions.CustomerNotFoundException;
import com.hexaware.electronics.exceptions.InvalidDataException;

public class CustomerDao implements ICustomerDao{

    Connection conn;

    public CustomerDao() {
        try {
			conn = DBUtil.getDBConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @Override
    public int addCustomer(Customer customer) throws InvalidDataException {
        if (customer.getFirstName() == null || customer.getEmail() == null) {
            throw new InvalidDataException("Customer must have a name and email");
        }

        try {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO customers VALUES (?, ?, ?, ?, ?, ?, ?)");
            stmt.setInt(1, customer.getCustomerID());
            stmt.setString(2, customer.getFirstName());
            stmt.setString(3, customer.getLastName());
            stmt.setString(4, customer.getEmail());
            stmt.setString(5, customer.getPhone());
            stmt.setString(6, customer.getAddress());
            stmt.setInt(7, customer.getTotalOrders());

            return stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int updateCustomer(Customer customer) throws CustomerNotFoundException, InvalidDataException {
        if (customer.getFirstName() == null || customer.getEmail() == null) {
            throw new InvalidDataException("Customer must have a name and email");
        }

        try {
            PreparedStatement stmt = conn.prepareStatement(
                "UPDATE customers SET firstName=?, lastName=?, email=?, phone=?, address=?, totalOrders=? WHERE customerID=?");
            stmt.setString(1, customer.getFirstName());
            stmt.setString(2, customer.getLastName());
            stmt.setString(3, customer.getEmail());
            stmt.setString(4, customer.getPhone());
            stmt.setString(5, customer.getAddress());
            stmt.setInt(6, customer.getTotalOrders());
            stmt.setInt(7, customer.getCustomerID());

            int rows = stmt.executeUpdate();
            if (rows == 0) {
                throw new CustomerNotFoundException("Customer not found with ID: " + customer.getCustomerID());
            }
            return rows;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int deleteCustomer(int customerId) throws CustomerNotFoundException {
        try {
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM customers WHERE customerID = ?");
            stmt.setInt(1, customerId);
            int rows = stmt.executeUpdate();
            if (rows == 0) {
                throw new CustomerNotFoundException("Customer not found to delete with ID: " + customerId);
            }
            return rows;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public Customer getCustomerById(int customerId) throws CustomerNotFoundException {
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM customers WHERE customerID = ?");
            stmt.setInt(1, customerId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Customer(
                    rs.getInt("customerID"),
                    rs.getString("firstName"),
                    rs.getString("lastName"),
                    rs.getString("email"),
                    rs.getString("phone"),
                    rs.getString("address"),
                    rs.getInt("totalOrder")
                );
            } else {
                throw new CustomerNotFoundException("Customer not found with ID: " + customerId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Customer> getAllCustomers() {
        List<Customer> list = new ArrayList<>();
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM customers");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                list.add(new Customer(
                    rs.getInt("customerID"),
                    rs.getString("firstName"),
                    rs.getString("lastName"),
                    rs.getString("email"),
                    rs.getString("phone"),
                    rs.getString("address"),
                    rs.getInt("totalOrders")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
} 
