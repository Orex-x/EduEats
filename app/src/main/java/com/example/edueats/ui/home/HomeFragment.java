package com.example.edueats.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.edueats.R;
import com.example.edueats.adapters.ProductAdapter;
import com.example.edueats.interfaces.IProductAdapter;
import com.example.edueats.models.Product;
import com.example.edueats.services.ApiClient;
import com.example.edueats.services.SingletonService;

import java.util.ArrayList;

public class HomeFragment extends Fragment implements IProductAdapter {

    private ListView list_product;
    ArrayList<Product> products;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_home, container, false);

        list_product = v.findViewById(R.id.list_product);


        products = new ArrayList<>(ApiClient.get(Product.class));
        ProductAdapter adapter = new ProductAdapter(getContext(), R.layout.item_product, products);
        adapter.setIProductAdapter(this);
        list_product.setAdapter(adapter);

        return v;
    }

    @Override
    public void addToBasket(int position) {
        Product product = products.get(position);
        SingletonService.mainClient.addProductToBasket(product);
        ApiClient.update(SingletonService.mainClient);
    }

    @Override
    public void addToFavorite(int position) {
        Product product = products.get(position);
        SingletonService.mainClient.addProductToFavorite(product);
        ApiClient.update(SingletonService.mainClient);
    }

    @Override
    public void deleteToFavorite(int position) {

    }

    @Override
    public void goToBasket() {

    }
}