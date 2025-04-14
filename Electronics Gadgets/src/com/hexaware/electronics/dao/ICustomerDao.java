package com.hexaware.electronics.dao;

import java.util.List;
import com.hexaware.electronics.entity.Customer;
import com.hexaware.electronics.exceptions.CustomerNotFoundException;
import com.hexaware.electronics.exceptions.InvalidDataException;

public interface ICustomerDao {

    int addCustomer(Customer customer) throws InvalidDataException;

    int updateCustomer(Customer customer) throws CustomerNotFoundException, InvalidDataException;

    int deleteCustomer(int customerId) throws CustomerNotFoundException;

    Customer getCustomerById(int customerId) throws CustomerNotFoundException;

    List<Customer> getAllCustomers();
} 