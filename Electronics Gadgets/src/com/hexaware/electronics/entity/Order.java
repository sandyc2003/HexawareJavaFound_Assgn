package com.hexaware.electronics.entity;


import java.time.LocalDateTime;
import java.util.List;

public class Order {

    // Attributes
    private int orderID;
    private Customer customer;               // Composition: Order has-a Customer
    private LocalDateTime orderDate;
    private double totalAmount;
    private String status;
    private List<Product> productList;       // Product list assumed for order details

    // Default Constructor
    public Order() {
    }

    // Parameterized Constructor
    public Order(int orderID, Customer customer, LocalDateTime orderDate, List<Product> productList, String status) {
        this.orderID = orderID;
        this.customer = customer;
        this.orderDate = orderDate;
        this.productList = productList;
        this.status = status;
        this.totalAmount = calculateTotalAmount();  // Initialize total using method
    }

    // Getters and Setters
    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Method 1: CalculateTotalAmount
    public double calculateTotalAmount() {
        double total = 0.0;
        for (Product p : productList) {
            total += p.getPrice();  // You can expand this later with quantity
        }
        return total;
    }

    // Method 2: GetOrderDetails
    public void getOrderDetails() {
        System.out.println("Order ID: " + orderID);
        System.out.println("Customer: " + customer.getFirstName() + " " + customer.getLastName());
        System.out.println("Order Date: " + orderDate);
        System.out.println("Status: " + status);
        System.out.println("Products:");
        for (Product p : productList) {
            System.out.println("- " + p.getProductName() + " (₹" + p.getPrice() + ")");
        }
        System.out.println("Total Amount: ₹" + totalAmount);
    }

    // Method 3: UpdateOrderStatus
    public void updateOrderStatus(String newStatus) {
        this.status = newStatus;
        System.out.println("Order status updated to: " + status);
    }

    // Method 4: CancelOrder
    public void cancelOrder() {
        this.status = "Cancelled";
        System.out.println("Order has been cancelled. Stock levels should be adjusted.");
        // You can add stock-restoration logic here if needed.
    }

    // Main for testing
    public static void main(String[] args) {
      
    }

	@Override
	public String toString() {
		return "Order [orderID=" + orderID + ", customer=" + customer + ", orderDate=" + orderDate + ", totalAmount="
				+ totalAmount + ", status=" + status + ", productList=" + productList + "]";
	}
}
