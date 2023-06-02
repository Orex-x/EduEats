package com.example.edueats.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.edueats.R;
import com.example.edueats.interfaces.ICardAdapter;
import com.example.edueats.interfaces.IOrderAdapter;
import com.example.edueats.models.BankCard;
import com.example.edueats.models.Order;
import com.example.edueats.models.ProductBasket;
import com.example.edueats.services.SingletonService;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class OrderAdapter extends ArrayAdapter<Order> {

    private LayoutInflater inflater;
    private int layout;
    private ArrayList<Order> list;
    private static IOrderAdapter mListener;

    public OrderAdapter(Context context, int resource, ArrayList<Order> list, IOrderAdapter listener) {
        super(context, resource, list);
        this.list = list;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
        mListener = listener;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        final ViewHolder viewHolder;

        if(convertView == null){
            convertView = inflater.inflate(this.layout, parent, false);
            viewHolder = new ViewHolder(convertView, position);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        final Order item = list.get(position);

        viewHolder.txtDate.setText(dateFormat.format(item.getDateTime()));
        viewHolder.txtSum.setText(String.valueOf(item.getPrice()));

        String menu = "";
        for (ProductBasket pb : item.getProducts()){
            menu += " - " + pb.getProduct().getName() + " | " + pb.getAmount() + "\n";
        }
        viewHolder.txtProducts.setText(menu);

        return convertView;
    }

    private class ViewHolder {
        final TextView txtDate, txtProducts, txtSum;

        ViewHolder(View view, int position){
            txtDate = view.findViewById(R.id.txtDate);
            txtProducts = view.findViewById(R.id.txtProducts);
            txtSum = view.findViewById(R.id.txtSum);
        }
    }
}
