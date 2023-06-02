package com.example.edueats.ui.dashboard;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.edueats.AddCardActivity;
import com.example.edueats.CheckActivity;
import com.example.edueats.PayActivity;
import com.example.edueats.interfaces.IBasketAdapter;
import com.example.edueats.R;
import com.example.edueats.adapters.BasketAdapter;
import com.example.edueats.interfaces.IDashboardFragment;
import com.example.edueats.models.ProductBasket;
import com.example.edueats.services.ApiClient;
import com.example.edueats.services.SingletonService;

import java.util.List;

public class DashboardFragment extends Fragment implements IBasketAdapter {


    private TextView txtSum;
    private ListView listView;
    private Button btnBuy;

    List<ProductBasket> products;
    BasketAdapter adapter;

    private IDashboardFragment mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof IDashboardFragment) {
            mListener = (IDashboardFragment) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement FragmentInteractionListener");
        }
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_dashboard, container, false);

        txtSum = v.findViewById(R.id.txtSum);
        listView = v.findViewById(R.id.listView);
        btnBuy = v.findViewById(R.id.btnBuy);

        if(products != null) products.clear();

        products = SingletonService.mainClient.getBasket();
        adapter = new BasketAdapter(getContext(), R.layout.item_product_basket, products);
        adapter.setIBasketAdapter(this);
        listView.setAdapter(adapter);
        setSum();

        btnBuy.setOnClickListener(view -> {
            SingletonService.sumOrder = Integer.parseInt(txtSum.getText().toString());
            Intent intent = new Intent(getContext(), PayActivity.class);
            startActivity(intent);
        });

        return v;
    }


    @Override
    public void increment(int position) {
        ProductBasket product = products.get(position);
        product.increment();
        ApiClient.update(product);
        adapter.notifyDataSetChanged();
        setSum();
    }

    @Override
    public void decrement(int position) {
        ProductBasket product = products.get(position);
        product.decrement();
        ApiClient.update(product);
        adapter.notifyDataSetChanged();
        setSum();
    }

    @Override
    public void delete(int position) {
        ProductBasket product = products.get(position);
        ApiClient.delete(product.getId(), ProductBasket.class);
        products.remove(product);
        adapter.notifyDataSetChanged();
        setSum();
    }

    public void setSum(){
        int sum = 0;

        for (ProductBasket productBasket : products){
            sum += productBasket.getAmount() * productBasket.getProduct().getPrice();
        }

        txtSum.setText(String.valueOf(sum));
    }
}