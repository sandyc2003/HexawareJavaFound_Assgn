package com.hexaware.electronics.main;

import java.util.*;
import java.sql.Connection;
import com.hexaware.electronics.dao.DBUtil;
import com.hexaware.electronics.entity.*;
import com.hexaware.electronics.services.*;
import com.hexaware.electronics.exceptions.*;

public class MainModule {

    static Scanner sc = new Scanner(System.in);
    static IElectronicsService service = new ElectronicsServiceImp();

    public static void main(String[] args) {
        // DB Connection Check 
        try {
            Connection conn = DBUtil.getDBConnection();
            if (conn != null) {
                System.out.println("\u2705 Connected to TechShopDB successfully!");
                conn.close();
            } else {
                System.out.println("\u274C Failed to connect to TechShopDB.");
            }
        } catch (Exception e) {
            System.out.println("\u26A0\uFE0F DB connection error: " + e.getMessage());
        }

        int choice = 0;
        do {
            System.out.println("\n--- TechShop Menu ---");
            System.out.println("1. Add Product");
            System.out.println("2. View Product By ID");
            System.out.println("3. List All Products");
            System.out.println("4. Add Customer");
            System.out.println("5. View Customer By ID");
            System.out.println("6. Place Order");
            System.out.println("7. Cancel Order");
            System.out.println("8. Exit Application");
            System.out.print("Enter choice: ");

            choice = sc.nextInt();
            sc.nextLine(); 

            try {
                switch (choice) {
                    case 1:
                        addProduct();
                        break;
                    case 2:
                        viewProductById();
                        break;
                    case 3:
                        listAllProducts();
                        break;
                    case 4:
                        addCustomer();
                        break;
                    case 5:
                        viewCustomerById();
                        break;
                    case 6:
                    	placeOrder();
                        break;
                    case 7:
                    	cancelOrder();
                    	break;
                    case 8:
                    	System.out.println("Successfully Exited");
                    	break;
                    default:
                        System.out.println("Invalid choice. Try again.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }

        } while (choice != 8);
    }

    private static void addProduct() throws InvalidDataException {
        System.out.print("Enter Product ID: ");
        int id = sc.nextInt(); sc.nextLine();
        System.out.print("Enter Product Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Description: ");
        String desc = sc.nextLine();
        System.out.print("Enter Price: ");
        double price = sc.nextDouble();
        Product p = new Product(id, name, desc, price, true);
        int rows = service.addProduct(p);
        System.out.println(rows + " product added.");
    }

    private static void viewProductById() throws ProductNotFoundException {
        System.out.print("Enter Product ID: ");
        int id = sc.nextInt();
        Product p = service.getProductById(id);
        System.out.println(p);
    }

    private static void listAllProducts() {
        List<Product> list = service.getAllProducts();
        for (Product p : list) {
            System.out.println(p);
        }
    }

    private static void addCustomer() throws InvalidDataException {
        System.out.print("Enter Customer ID: ");
        int id = sc.nextInt(); sc.nextLine();
        System.out.print("Enter First Name: ");
        String fname = sc.nextLine();
        System.out.print("Enter Last Name: ");
        String lname = sc.nextLine();
        System.out.print("Enter Email: ");
        String email = sc.nextLine();
        System.out.print("Enter Phone: ");
        String phone = sc.nextLine();
        System.out.print("Enter Address: ");
        String addr = sc.nextLine();
        Customer c = new Customer(id, fname, lname, email, phone, addr, 0);
        int rows = service.addCustomer(c);
        System.out.println(rows + " customer added.");
    }

    private static void viewCustomerById() throws CustomerNotFoundException {
        System.out.print("Enter Customer ID: ");
        int id = sc.nextInt();
        Customer c = service.getCustomerById(id);
        System.out.println(c);
    }
    private static void placeOrder() throws InvalidDataException {
        System.out.print("Enter Order ID: ");
        int orderId = sc.nextInt(); sc.nextLine();

        System.out.print("Enter Customer ID: ");
        int custId = sc.nextInt();
        Customer customer;
        try {
            customer = service.getCustomerById(custId);
        } catch (CustomerNotFoundException e) {
            System.out.println("Customer not found with ID: " + custId);
            return; 
        }

        List<Product> products = new ArrayList<>();
        System.out.print("Enter number of products to order: ");
        int count = sc.nextInt();
        for (int i = 0; i < count; i++) {
            System.out.print("Enter Product ID: ");
            int pid = sc.nextInt();
            try {
                Product prod = service.getProductById(pid);
                products.add(prod);
            } catch (ProductNotFoundException e) {
                System.out.println("Product not found with ID: " + pid);
                return;
            }
        }

        System.out.print("Enter Status: ");
        sc.nextLine(); 
        String status = sc.nextLine();

        Order order = new Order(orderId, customer, java.time.LocalDateTime.now(), products, status);
        try {
            int rows = service.placeOrder(order);
            System.out.println(rows + " order placed.");
        } catch (InsufficientStockException | IncompleteOrderException e) {
            System.out.println("Order failed: " + e.getMessage());
        }
    }
    private static void cancelOrder() {
        System.out.print("Enter Order ID to cancel: ");
        int id = sc.nextInt();
        try {
            int rows = service.cancelOrder(id);
            System.out.println(rows + " order cancelled.");
        } catch (OrderNotFoundException e) {
            System.out.println("Order not found with ID: " + id);
        } catch (ConcurrencyException e) {
            System.out.println("Concurrency issue: Order may have been modified by another process.");
        }
    }


} 
