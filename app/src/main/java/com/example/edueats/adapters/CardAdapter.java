package com.example.edueats.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.example.edueats.R;
import com.example.edueats.interfaces.ICardAdapter;
import com.example.edueats.models.BankCard;

import java.util.ArrayList;

public class CardAdapter extends ArrayAdapter<BankCard> {

    private LayoutInflater inflater;
    private int layout;
    private ArrayList<BankCard> list;
    private static ICardAdapter mListener;

    public CardAdapter(Context context, int resource, ArrayList<BankCard> list, ICardAdapter listener) {
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

        final BankCard item = list.get(position);

        viewHolder.txtCardNumber.setText(item.getNumber());
        viewHolder.txtFIO.setText(item.getFIO());
        viewHolder.txtCardDate.setText(item.getDate());

        return convertView;
    }

    private class ViewHolder {

        final TextView txtCardNumber, txtCardDate, txtFIO;
        final ConstraintLayout layout;

        ViewHolder(View view, int position){
            txtCardNumber = view.findViewById(R.id.txtCardNumber);
            txtCardDate = view.findViewById(R.id.txtCardDate);
            txtFIO = view.findViewById(R.id.txtFIO);

            layout = view.findViewById(R.id.cardItemId);

            layout.setOnClickListener(x -> {mListener.click(position);});
        }
    }
}
