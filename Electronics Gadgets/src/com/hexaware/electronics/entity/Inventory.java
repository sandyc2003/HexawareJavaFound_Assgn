package com.hexaware.electronics.entity;


import java.time.LocalDateTime;

public class Inventory {
	private int inventoryID;
    private Product product;
    private int quantityInStock;
    private LocalDateTime lastStockUpdate;

    // Constructor
    public Inventory(int inventoryID, Product product, int quantityInStock) {
        this.inventoryID = inventoryID;
        this.product = product;
        this.quantityInStock = quantityInStock;
        this.lastStockUpdate = LocalDateTime.now();
    }

    // Getters
    public int getInventoryID() {
        return inventoryID;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantityInStock() {
        return quantityInStock;
    }

    public LocalDateTime getLastStockUpdate() {
        return lastStockUpdate;
    }

    
    public void addToInventory(int quantity) {
        this.quantityInStock += quantity;
        this.lastStockUpdate = LocalDateTime.now();
        System.out.println(quantity + " items added to inventory.");
    }

    
    public void removeFromInventory(int quantity) {
        if (quantity <= this.quantityInStock) {
            this.quantityInStock -= quantity;
            this.lastStockUpdate = LocalDateTime.now();
            System.out.println(quantity + " items removed from inventory.");
        } else {
            System.out.println("Not enough stock to remove " + quantity + " items.");
        }
    }

    
    public void updateStockQuantity(int newQuantity) {
        this.quantityInStock = newQuantity;
        this.lastStockUpdate = LocalDateTime.now();
        System.out.println("Stock quantity updated to " + newQuantity);
    }

    
    public boolean isProductAvailable(int quantityToCheck) {
        return this.quantityInStock >= quantityToCheck;
    }

    
    public double getInventoryValue() {
        return product.getPrice() * quantityInStock;
    }

    
    public void listLowStockProducts(int threshold) {
        if (this.quantityInStock < threshold) {
            System.out.println("Low Stock: " + product.getProductName() + " - Only " + quantityInStock + " left.");
        }
    }

    
    public void listOutOfStockProducts() {
        if (this.quantityInStock == 0) {
            System.out.println("Out of Stock: " + product.getProductName());
        }
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	@Override
	public String toString() {
		return "Inventory [inventoryID=" + inventoryID + ", product=" + product + ", quantityInStock=" + quantityInStock
				+ ", lastStockUpdate=" + lastStockUpdate + "]";
	}

}
