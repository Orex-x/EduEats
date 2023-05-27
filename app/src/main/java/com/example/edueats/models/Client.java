package com.example.edueats.models;

import com.example.edueats.services.ApiClient;

import java.util.ArrayList;
import java.util.List;

public class Client {

    private int id;

    private int userId;

    private User user;

    private int balance;

    private List<FavoriteProduct> favoriteProducts;

    private List<ProductBasket> basket;

    private List<Order> orders;

    private List<BankCard> bankCards;

    public Client() {
    }


    public void addBankCard(BankCard card){
        if(bankCards == null) bankCards = new ArrayList<>();
        this.bankCards.add(card);
    }


    public void addProductToBasket(Product product){
        ProductBasket pb = new ProductBasket(0, product, 1, 0);
        this.basket.add(pb);
    }

    public void addProductToFavorite(Product product){
        FavoriteProduct favoriteProduct = new FavoriteProduct(0, product, product.getId());
        this.favoriteProducts.add(favoriteProduct);
    }

    public void removeProductToFavorite(FavoriteProduct product){
        this.favoriteProducts.remove(product);
    }

    public void spend(int x){
        this.balance -= x;
    }

    public void upBalance(int x){
        this.balance += x;
    }


    public void clearBasket(){
        for (ProductBasket pb : basket){
            ApiClient.delete(pb.getId(), ProductBasket.class);
        }
        basket.clear();
    }


    //public void incrementProductInBasket(int)


    public Client(int id, int userId, User user, int balance, List<BankCard> bankCards, List<FavoriteProduct> favoriteProducts, List<ProductBasket> basket, List<Order> orders) {
        this.id = id;
        this.userId = userId;
        this.user = user;
        this.balance = balance;
        this.bankCards = bankCards;
        this.favoriteProducts = favoriteProducts;
        this.basket = basket;
        this.orders = orders;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public List<FavoriteProduct> getFavoriteProducts() {

        return favoriteProducts;
    }

    public void setFavoriteProducts(List<FavoriteProduct> favoriteProducts) {
        this.favoriteProducts = favoriteProducts;
    }

    public List<ProductBasket> getBasket() {
        return basket;
    }

    public void setBasket(List<ProductBasket> basket) {
        this.basket = basket;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public List<BankCard> getBankCards() {
        return bankCards;
    }

    public void setBankCards(List<BankCard> bankCards) {
        this.bankCards = bankCards;
    }
}
