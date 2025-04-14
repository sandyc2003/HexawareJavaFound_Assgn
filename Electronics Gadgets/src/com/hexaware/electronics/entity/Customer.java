package com.hexaware.electronics.entity;

public class Customer {

		    // Attributes
		    private int customerID;
		    private String firstName;
		    private String lastName;
		    private String email;
		    private String phone;
		    private String address;

		    // Simulated field for number of orders (in real-world, this might come from a database)
		    private int totalOrders;

		    // Default Constructor
		    public Customer() {
		    }

		    // Parameterized Constructor
		    public Customer(int customerID, String firstName, String lastName, String email, String phone, String address, int totalOrders) {
		        this.customerID = customerID;
		        this.firstName = firstName;
		        this.lastName = lastName;
		        this.email = email;
		        this.phone = phone;
		        this.address = address;
		        this.totalOrders = totalOrders;
		    }

		    // Getters and Setters
		    public int getCustomerID() {
		        return customerID;
		    }

		    public void setCustomerID(int customerID) {
		        this.customerID = customerID;
		    }

		    public String getFirstName() {
		        return firstName;
		    }

		    public void setFirstName(String firstName) {
		        this.firstName = firstName;
		    }

		    public String getLastName() {
		        return lastName;
		    }

		    public void setLastName(String lastName) {
		        this.lastName = lastName;
		    }

		    public String getEmail() {
		        return email;
		    }

		    public void setEmail(String email) {
		        this.email = email;
		    }

		    public String getPhone() {
		        return phone;
		    }

		    public void setPhone(String phone) {
		        this.phone = phone;
		    }

		    public String getAddress() {
		        return address;
		    }

		    public void setAddress(String address) {
		        this.address = address;
		    }

		    public int getTotalOrders() {
		        return totalOrders;
		    }

		    public void setTotalOrders(int totalOrders) {
		        this.totalOrders = totalOrders;
		    }

		    // Method 1: CalculateTotalOrders
		    public int calculateTotalOrders() {
		        // In a real-world app, this might fetch data from an Orders table.
		        return totalOrders;
		    }

		    // Method 2: GetCustomerDetails
		    public void getCustomerDetails() {
		        System.out.println("Customer Details:");
		        System.out.println("ID: " + customerID);
		        System.out.println("Name: " + firstName + " " + lastName);
		        System.out.println("Email: " + email);
		        System.out.println("Phone: " + phone);
		        System.out.println("Address: " + address);
		        System.out.println("Total Orders: " + totalOrders);
		    }

		    // Method 3: UpdateCustomerInfo
		      public void updateCustomerInfo(String newEmail, String newPhone, String newAddress) {
		        this.email = newEmail;
		        this.phone = newPhone;
		        this.address = newAddress;
		        System.out.println("Customer information updated successfully.");
		    }
		      public static void main(String[] args) {
		  		// TODO Auto-generated method stub
		    	  
		  	}

			@Override
			public String toString() {
				return "Customer [customerID=" + customerID + ", firstName=" + firstName + ", lastName=" + lastName
						+ ", email=" + email + ", phone=" + phone + ", address=" + address + ", totalOrders="
						+ totalOrders + "]";
			}
		

	}

