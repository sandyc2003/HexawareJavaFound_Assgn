package com.hexaware.electronics.entity;

public class OrderDetails {
	 private int orderDetailID;
	    private Order order;
	    private Product product;
	    private int quantity;
	    private double discount; 
	    
	    // Constructors
	    public OrderDetails() {
	    }

	    public OrderDetails(int orderDetailID, Order order, Product product, int quantity) {
	        this.orderDetailID = orderDetailID;
	        this.order = order;
	        this.product = product;
	        this.quantity = quantity;
	        this.discount = 0.0;
	    }

	    // Getters and Setters
	    public int getOrderDetailID() {
	        return orderDetailID;
	    }

	    public void setOrderDetailID(int orderDetailID) {
	        this.orderDetailID = orderDetailID;
	    }

	    public Order getOrder() {
	        return order;
	    }

	    public void setOrder(Order order) {
	        this.order = order;
	    }

	    public Product getProduct() {
	        return product;
	    }

	    public void setProduct(Product product) {
	        this.product = product;
	    }

	    public int getQuantity() {
	        return quantity;
	    }

	    public void setQuantity(int quantity) {
	        this.quantity = quantity;
	    }

	    public double getDiscount() {
	        return discount;
	    }

	    // Method 1: CalculateSubtotal
	    public double calculateSubtotal() {
	        double subtotal = product.getPrice() * quantity;
	        if (discount > 0) {
	            subtotal = subtotal - (subtotal * discount / 100);
	        }
	        return subtotal;
	    }

	    // Method 2: GetOrderDetailInfo
	    public void getOrderDetailInfo() {
	        System.out.println("Order Detail Info:");
	        System.out.println("OrderDetail ID: " + orderDetailID);
	        System.out.println("Product: " + product.getProductName());
	        System.out.println("Quantity: " + quantity);
	        System.out.println("Price per unit: ₹" + product.getPrice());
	        System.out.println("Discount: " + discount + "%");
	        System.out.println("Subtotal: ₹" + calculateSubtotal());
	    }

	    // Method 3: UpdateQuantity
	    public void updateQuantity(int newQuantity) {
	        this.quantity = newQuantity;
	        System.out.println("Quantity updated to " + newQuantity);
	    }

	    // Method 4: AddDiscount
	    public void addDiscount(double discountPercent) {
	        this.discount = discountPercent;
	        System.out.println("Discount of " + discountPercent + "% applied.");
	    }


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		

	}

	@Override
	public String toString() {
		return "OrderDetails [orderDetailID=" + orderDetailID + ", order=" + order + ", product=" + product
				+ ", quantity=" + quantity + ", discount=" + discount + "]";
	}

}
