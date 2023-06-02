package com.example.edueats.ui.orders;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.edueats.R;
import com.example.edueats.adapters.OrderAdapter;
import com.example.edueats.interfaces.IOrderAdapter;
import com.example.edueats.models.Order;
import com.example.edueats.services.SingletonService;

import java.util.ArrayList;
import java.util.List;

public class OrdersFragment extends Fragment implements IOrderAdapter {

    ListView list_orders;
    OrderAdapter adapter;
    ArrayList<Order> orders;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_orders, container, false);

        orders = new ArrayList<>(SingletonService.mainClient.getOrders());

        list_orders = v.findViewById(R.id.list);
        adapter = new OrderAdapter(getContext(), R.layout.item_order, orders, this);
        list_orders.setAdapter(adapter);

        return v;
    }

    @Override
    public void open(int position) {

    }
}