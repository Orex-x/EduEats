package com.example.edueats.models;

import java.util.Date;
import java.util.List;

public class Order {

    private int id;

    private OrderStatus orderStatus;

    private List<ProductBasket> products;

    private Date dateTime;

    private int price;

    public Order() {
    }

    public Order(int id, OrderStatus orderStatus, List<ProductBasket> products, Date dateTime, int price) {
        this.id = id;
        this.orderStatus = orderStatus;
        this.products = products;
        this.dateTime = dateTime;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public List<ProductBasket> getProducts() {
        return products;
    }

    public void setProducts(List<ProductBasket> products) {
        this.products = products;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
