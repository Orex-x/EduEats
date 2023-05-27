package com.example.edueats;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.edueats.models.ProductBasket;
import com.example.edueats.services.ApiClient;
import com.example.edueats.services.SingletonService;

import java.util.Random;

public class CheckActivity extends AppCompatActivity {

    private TextView txtCode, txtMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);

        txtCode = findViewById(R.id.txtCode);
        txtMenu = findViewById(R.id.txtMenu);

        Random random = new Random();
        int randomNumber = random.nextInt(8888) + 1111;
        txtCode.setText(String.valueOf(randomNumber));


        String menu = "";
        for (ProductBasket pb : SingletonService.mainClient.getBasket()){
            menu += " - " + pb.getProduct().getName() + " | " + pb.getAmount() + "\n";
        }

        txtMenu.setText(menu);

        SingletonService.mainClient.clearBasket();
        ApiClient.update(SingletonService.mainClient);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}