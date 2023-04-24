package com.example.edueats.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.edueats.R;
import com.example.edueats.interfaces.IBasketAdapter;
import com.example.edueats.interfaces.IProductAdapter;
import com.example.edueats.models.Product;

import java.util.ArrayList;

public class ProductAdapter extends ArrayAdapter<Product> {

    private LayoutInflater inflater;
    private int layout;
    private ArrayList<Product> productList;

    public ProductAdapter(Context context, int resource, ArrayList<Product> products) {
        super(context, resource, products);
        this.productList = products;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
    }

    private static IProductAdapter mListener;
    public void setIProductAdapter(IProductAdapter listener) {
        mListener = listener;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        final ViewHolder viewHolder;
        if(convertView==null){
            convertView = inflater.inflate(this.layout, parent, false);
            viewHolder = new ViewHolder(convertView, position);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        final Product product = productList.get(position);

        viewHolder.txtName.setText(product.getName());
        viewHolder.txtDescription.setText(product.getDescription());
        viewHolder.txtPrice.setText(String.valueOf(product.getPrice()));

        return convertView;
    }
    private class ViewHolder {

        final TextView txtName;
        final TextView txtPrice;
        final TextView txtDescription;
        final ImageView imageView;
        final Button btn;

        ViewHolder(View view, int position){
            txtName = view.findViewById(R.id.txtName);
            txtPrice = view.findViewById(R.id.txtPrice);
            txtDescription = view.findViewById(R.id.txtDescription);
            imageView = view.findViewById(R.id.imageView);
            btn = view.findViewById(R.id.btn);

            btn.setOnClickListener(v -> mListener.addToBasket(position));
        }
    }
}
