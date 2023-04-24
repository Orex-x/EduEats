package com.example.edueats.models;

import java.util.List;

public class Combo {
    private int id;

    private String name;

    private String pathToImage;

    private int price;

    private List<Product> products;

    public Combo() {
    }

    public Combo(int id, String name, String pathToImage, int price, List<Product> products) {
        this.id = id;
        this.name = name;
        this.pathToImage = pathToImage;
        this.price = price;
        this.products = products;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPathToImage() {
        return pathToImage;
    }

    public void setPathToImage(String pathToImage) {
        this.pathToImage = pathToImage;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
