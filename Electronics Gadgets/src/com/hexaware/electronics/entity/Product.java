package com.hexaware.electronics.entity;

public class Product {
	private int productID;
    private String productName;
    private String description;
    private double price;         
    private boolean inStock;      

    // Default Constructor
    public Product(int productId, String productName, String description, double price, boolean inStock) {
    	
    	    this.productID = productId;
    	    this.productName = productName;
    	    this.description = description;
    	    this.price = price;
    	    this.inStock = inStock;
    	}


    // Getters and Setters
    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isInStock() {
        return inStock;
    }

    public void setInStock(boolean inStock) {
        this.inStock = inStock;
    }

    // Method 1: GetProductDetails
    public void getProductDetails() {
        System.out.println("Product Details:");
        System.out.println("ID: " + productID);
        System.out.println("Name: " + productName);
        System.out.println("Description: " + description);
        System.out.println("Price: ₹" + price);
        System.out.println("Availability: " + (inStock ? "In Stock" : "Out of Stock"));
    }

    // Method 2: UpdateProductInfo
    public void updateProductInfo(double newPrice, String newDescription) {
        this.price = newPrice;
        this.description = newDescription;
        System.out.println("Product information updated successfully.");
    }

    // Method 3: IsProductInStock
    public boolean isProductInStock() {
        return inStock;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	@Override
	public String toString() {
		return "Product [productID=" + productID + ", productName=" + productName + ", description=" + description
				+ ", price=" + price + ", inStock=" + inStock + "]";
	}
	}


