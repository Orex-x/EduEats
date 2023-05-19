package com.example.edueats.ui.favorite;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.edueats.R;
import com.example.edueats.adapters.ProductAdapter;
import com.example.edueats.adapters.ProductFavoriteAdapter;
import com.example.edueats.interfaces.IProductAdapter;
import com.example.edueats.models.Product;
import com.example.edueats.services.ApiClient;
import com.example.edueats.services.SingletonService;

import java.util.ArrayList;


public class FavotiteProductFragment extends Fragment implements IProductAdapter {

    private ListView list_product;
    ArrayList<Product> products;
    ProductFavoriteAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_favotite_product, container, false);

        list_product = v.findViewById(R.id.list_product);

        products = new ArrayList<>(SingletonService.mainClient.getFavoriteProducts());
        adapter = new ProductFavoriteAdapter(getContext(), R.layout.item_favorite_product, products);
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

    }

    @Override
    public void deleteToFavorite(int position) {
        Product product = products.get(position);
        SingletonService.mainClient.deleteProductToFavorite(product);
        ApiClient.update(SingletonService.mainClient);

        products.remove(product);
        adapter.notifyDataSetChanged();
    }
}