package com.example.edueats;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.edueats.adapters.CardAdapter;
import com.example.edueats.interfaces.ICardAdapter;
import com.example.edueats.models.BankCard;
import com.example.edueats.services.ApiClient;
import com.example.edueats.services.SingletonService;

import java.util.ArrayList;
import java.util.List;

public class PayActivity extends AppCompatActivity implements ICardAdapter {

    ListView lvCards;
    Button btnAddCard, btnPay;
    ArrayList<BankCard> cardList;
    CardAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        int sum = SingletonService.sumOrder;

        lvCards = findViewById(R.id.lvCards);
        btnAddCard = findViewById(R.id.btnAddCard);
        btnPay = findViewById(R.id.btnPay);

        cardList = new ArrayList<>(SingletonService.mainClient.getBankCards());
        adapter = new CardAdapter(this, R.layout.card_item, cardList, this);
        lvCards.setAdapter(adapter);

        btnAddCard.setOnClickListener(v -> {
            Intent intent = new Intent(this, AddCardActivity.class);
            startActivity(intent);
        });

        if(sum > SingletonService.mainClient.getBalance()){
            btnPay.setEnabled(false);
        }

        btnPay.setOnClickListener(v -> {
            SingletonService.mainClient.spend(sum);

            ApiClient.update(SingletonService.mainClient);

            Intent intent = new Intent(this, CheckActivity.class);
            startActivity(intent);
        });

    }

    @Override
    public void click(int position) {
        SingletonService.mainClient.spend(SingletonService.sumOrder);

        ApiClient.update(SingletonService.mainClient);

        Intent intent = new Intent(this, CheckActivity.class);
        startActivity(intent);
    }
}