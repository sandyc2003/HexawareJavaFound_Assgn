package com.hexaware.electronics.services;

import java.util.List;
import com.hexaware.electronics.entity.*;
import com.hexaware.electronics.exceptions.*;
import com.hexaware.electronics.dao.*;

public class ElectronicsServiceImp implements IElectronicsService {

    IProductDao productDao = new ProductDao();
    ICustomerDao customerDAO = new CustomerDao();
    IOrderDao orderDAO = new OrderDao();
    IOrderDetailsDao orderDetailDao = new OrderDetailsDao();
    IInventoryDao inventoryDao = new InventoryDao();
    IPaymentDao paymentDAO = new PaymentDao();

    @Override
    public int addProduct(Product p) throws InvalidDataException {
        return productDao.addProduct(p);
    }

    @Override
    public int updateProduct(Product p) throws ProductNotFoundException, InvalidDataException {
        return productDao.updateProduct(p);
    }

    @Override
    public Product getProductById(int pid) throws ProductNotFoundException {
        return productDao.getProductById(pid);
    }

    @Override
    public List<Product> getAllProducts() {
        return productDao.getAllProducts();
    }

    @Override
    public int addCustomer(Customer c) throws InvalidDataException {
        return customerDAO.addCustomer(c);
    }

    @Override
    public Customer getCustomerById(int cid) throws CustomerNotFoundException {
        return customerDAO.getCustomerById(cid);
    }

    @Override
    public int placeOrder(Order o) throws InsufficientStockException, IncompleteOrderException {
        return orderDAO.placeOrder(o);
    }

    @Override
    public Order getOrderById(int oid) throws OrderNotFoundException {
        return orderDAO.getOrderById(oid);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderDAO.getAllOrders();
    }

    @Override
    public int cancelOrder(int oid) throws OrderNotFoundException, ConcurrencyException {
        return orderDAO.cancelOrder(oid);
    }

    @Override
    public int addInventory(Inventory inventory) {
        return inventoryDao.addInventory(inventory);
    }

    @Override
    public Inventory getInventoryByProductId(int pid) throws ProductNotFoundException {
        return inventoryDao.getInventoryByProductId(pid);
    }

    @Override
    public int updateInventory(int pid, int quantity) throws InsufficientStockException {
        return inventoryDao.updateInventory(pid, quantity);
    }

    @Override
    public int addOrderDetail(OrderDetails detail) throws InsufficientStockException, IncompleteOrderException {
        return orderDetailDao.addOrderDetail(detail);
    }

    @Override
    public int processPayment(int orderId, Payment payment) throws PaymentFailedException {
        return PaymentDao.processPayment(orderId, payment);
    }
} 
