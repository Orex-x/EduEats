package com.example.edueats;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.edueats.models.BankCard;
import com.example.edueats.services.ApiClient;
import com.example.edueats.services.SingletonService;

public class AddCardActivity extends AppCompatActivity {

    EditText txtCardNumber, txtCardYY, txtCardMM, txtCardCVC, txtCardFIO;
    Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card);

        txtCardNumber = findViewById(R.id.txtCardNumber);
        txtCardYY = findViewById(R.id.txtCardYY);
        txtCardMM = findViewById(R.id.txtCardMM);
        txtCardCVC = findViewById(R.id.txtCardCVC);
        txtCardFIO = findViewById(R.id.txtCardFIO);
        btnAdd = findViewById(R.id.btnAdd);

        btnAdd.setOnClickListener(v -> {
            try{
                String number = txtCardNumber.getText().toString();
                String yy = txtCardYY.getText().toString();
                String mm = txtCardMM.getText().toString();
                String cvc = txtCardCVC.getText().toString();
                String fio = txtCardFIO.getText().toString();

                BankCard card = new BankCard();
                card.setNumber(number);
                card.setDate(mm + "/" + yy);
                card.setCVC(cvc);
                card.setFIO(fio);

                SingletonService.mainClient.addBankCard(card);
                ApiClient.update(SingletonService.mainClient);
                Toast.makeText(this, "Карта успешно добавлена", Toast.LENGTH_SHORT).show();

                onBackPressed();
            }catch (Exception ex){
                Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}