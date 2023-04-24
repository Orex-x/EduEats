package com.example.edueats.models;

import java.util.List;

public class Category {

    private int id;

    private String name;

    private String pathToImage;

    private List<Product> products;

    public Category() {
    }

    public Category(int id, String name, String pathToImage, List<Product> products) {
        this.id = id;
        this.name = name;
        this.pathToImage = pathToImage;
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

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
