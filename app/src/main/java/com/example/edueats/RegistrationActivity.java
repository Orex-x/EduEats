package com.example.edueats;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.edueats.models.Client;
import com.example.edueats.models.User;
import com.example.edueats.services.ApiClient;
import com.example.edueats.services.SingletonService;

import java.util.ArrayList;
import java.util.List;

public class RegistrationActivity extends AppCompatActivity {

    EditText edtFirstName, edtSecondName, edtLastName, edtEmail, edtLogin, edtPassword;
    Button btnReg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        edtFirstName = findViewById(R.id.edtFirstName);
        edtSecondName = findViewById(R.id.edtSecondName);
        edtLastName = findViewById(R.id.edtLastName);
        edtEmail = findViewById(R.id.edtEmail);
        edtLogin = findViewById(R.id.edtLogin);
        edtPassword = findViewById(R.id.edtPassword);

        btnReg = findViewById(R.id.btnReg);

        btnReg.setOnClickListener(view -> {
            User user = new User();
            user.setFirstName(edtFirstName.getText().toString());
            user.setSecondName(edtSecondName.getText().toString());
            user.setLastName(edtLastName.getText().toString());
            user.setEmail(edtEmail.getText().toString());
            user.setLogin(edtLogin.getText().toString());
            user.setPassword(edtPassword.getText().toString());

            Client client = new Client();
            client.setUser(user);
            client.setBalance(0);
            client.setBankCards(new ArrayList<>());
            client.setFavoriteProducts(new ArrayList<>());
            client.setOrders(new ArrayList<>());
            client.setBasket(new ArrayList<>());

            List<Client> listClients = ApiClient.get(Client.class);

            for (Client cl : listClients) {
                if(cl.getUser().getEmail().equals(user.getEmail())){
                    Toast.makeText(this, "Почта занята", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(cl.getUser().getLogin().equals(user.getLogin())){
                    Toast.makeText(this, "Логин занят", Toast.LENGTH_SHORT).show();
                    return;
                }
            }

            int id = ApiClient.create(client);

            if(id != 0){
                SingletonService.mainClient = client;

                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                return;
            }
        });
    }
}