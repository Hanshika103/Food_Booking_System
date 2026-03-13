


package model;

import java.util.Date;

public class Order {

    private int orderId;
    private String userEmail;
    private String items;
    private int totalPrice;
    private Date orderDate;
    private boolean paid;
    private String status;

    // Constructor for placing new order
    public Order(String userEmail, String items, int totalPrice, Date orderDate, boolean paid) {
        this.userEmail = userEmail;
        this.items = items;
        this.totalPrice = totalPrice;
        this.orderDate = orderDate;
        this.paid = paid;
        this.status = "Preparing";
    }

    // Constructor for fetching order from database
    public Order(int orderId, String userEmail, String items, int totalPrice, Date orderDate, boolean paid, String status) {
        this.orderId = orderId;
        this.userEmail = userEmail;
        this.items = items;
        this.totalPrice = totalPrice;
        this.orderDate = orderDate;
        this.paid = paid;
        this.status = status;
    }

    // Getters

    public int getOrderId() {
        return orderId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getItems() {
        return items;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public boolean isPaid() {
        return paid;
    }

    public String getStatus() {
        return status;
    }

    // Setters

    public void setStatus(String status) {
        this.status = status;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }
}