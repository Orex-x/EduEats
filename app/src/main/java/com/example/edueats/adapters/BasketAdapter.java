package com.example.edueats.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.edueats.interfaces.IBasketAdapter;
import com.example.edueats.R;
import com.example.edueats.models.ProductBasket;

import java.util.List;

public class BasketAdapter extends ArrayAdapter<ProductBasket> {

    private LayoutInflater inflater;
    private int layout;
    private List<ProductBasket> productList;

    private static IBasketAdapter mListener;
    public void setIBasketAdapter(IBasketAdapter listener) {
        mListener = listener;
    }

    public BasketAdapter(Context context, int resource, List<ProductBasket> products) {
        super(context, resource, products);
        this.productList = products;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
    }


    public View getView(int position, View convertView, ViewGroup parent) {

        final BasketAdapter.ViewHolder viewHolder;
        if(convertView==null){
            convertView = inflater.inflate(this.layout, parent, false);
            viewHolder = new BasketAdapter.ViewHolder(convertView, position);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (BasketAdapter.ViewHolder) convertView.getTag();
        }


        final ProductBasket productBasket = productList.get(position);

        viewHolder.txtName.setText(productBasket.getProduct().getName());
        viewHolder.txtDescription.setText(productBasket.getProduct().getDescription());
        viewHolder.txtAmount.setText(String.valueOf(productBasket.getAmount()));

        int price = productBasket.getProduct().getPrice() * productBasket.getAmount();
        viewHolder.txtPrice.setText(String.valueOf(price));



        return convertView;
    }
    private class ViewHolder {

        final TextView txtName;
        final TextView txtPrice;
        final TextView txtDescription;
        final TextView txtAmount;
        final ImageView imageView;
        final Button btnPlus, btnMinus, btnDelete;

        ViewHolder(View view, int position){
            txtName = view.findViewById(R.id.txtName);
            txtPrice = view.findViewById(R.id.txtPrice);
            txtDescription = view.findViewById(R.id.txtDescription);
            txtAmount = view.findViewById(R.id.txtAmount);
            imageView = view.findViewById(R.id.imageView);
            btnPlus = view.findViewById(R.id.btnPlus);
            btnMinus = view.findViewById(R.id.btnMinus);
            btnDelete = view.findViewById(R.id.btnDelete);

            btnPlus.setOnClickListener(v -> mListener.increment(position));
            btnMinus.setOnClickListener(v -> mListener.decrement(position));
            btnDelete.setOnClickListener(v -> mListener.delete(position));
        }
    }
}
