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
import com.example.edueats.interfaces.IProductAdapter;
import com.example.edueats.models.FavoriteProduct;
import com.example.edueats.models.Product;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ProductFavoriteAdapter extends ArrayAdapter<FavoriteProduct> {

    private LayoutInflater inflater;
    private int layout;
    private ArrayList<FavoriteProduct> productList;

    public ProductFavoriteAdapter(Context context, int resource, ArrayList<FavoriteProduct> products) {
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
        final FavoriteProduct favoriteProduct = productList.get(position);

        viewHolder.txtName.setText(favoriteProduct.getProduct().getName());
        viewHolder.txtDescription.setText(favoriteProduct.getProduct().getDescription());
        viewHolder.txtPrice.setText(String.valueOf(favoriteProduct.getProduct().getPrice()));
        Picasso.get().load(favoriteProduct.getProduct().getPathToImage()).into(viewHolder.imageView);

        return convertView;
    }


    private class ViewHolder {

        final TextView txtName;
        final TextView txtPrice;
        final TextView txtDescription;
        final ImageView imageView;
        final Button btn, btnDel;

        ViewHolder(View view, int position){
            txtName = view.findViewById(R.id.txtName);
            txtPrice = view.findViewById(R.id.txtPrice);
            txtDescription = view.findViewById(R.id.txtDescription);
            imageView = view.findViewById(R.id.imageView);
            btn = view.findViewById(R.id.btn);
            btnDel = view.findViewById(R.id.btnDel);

            btn.setOnClickListener(v -> mListener.addToBasket(position));
            btnDel.setOnClickListener(v -> mListener.deleteToFavorite(position));
        }
    }
}
