package com.example.edueats.models;

public class ProductBasket {
    private int id;

    private int productId;

    private Product product;

    private int amount;

    private int discount; //для хранения скидки

    public void increment(){
        if(amount == product.getAmount())
            return;

        this.amount++;
    }

    public void decrement(){
        if(amount == 1)
            return;

        this.amount--;
    }

    public ProductBasket() {
    }

    public ProductBasket(int id, Product product, int amount, int discount) {
        this.id = id;
        this.product = product;
        this.amount = amount;
        this.discount = discount;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
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

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }
}
