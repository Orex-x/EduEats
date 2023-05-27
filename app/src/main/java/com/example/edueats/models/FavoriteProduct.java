package com.example.edueats.models;

public class FavoriteProduct {
    private int id;
    private Product product;
    private int productId;

    public FavoriteProduct() {
    }

    public FavoriteProduct(int id, Product product, int productId) {
        this.id = id;
        this.product = product;
        this.productId = productId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
}
