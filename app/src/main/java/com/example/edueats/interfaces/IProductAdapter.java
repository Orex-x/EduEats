package com.example.edueats.interfaces;

public interface IProductAdapter {
    void addToBasket(int position);
    void addToFavorite(int position);
    void deleteToFavorite(int position);

    void goToBasket();
}
