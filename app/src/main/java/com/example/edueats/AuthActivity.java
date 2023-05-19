package com.example.edueats;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.edueats.models.Client;
import com.example.edueats.services.ApiClient;
import com.example.edueats.services.SingletonService;

import java.util.List;

public class AuthActivity extends AppCompatActivity {

    private EditText edtLogin, edtPassword;
    private Button btnLogin, btnReg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        edtLogin = findViewById(R.id.edtLogin);
        edtPassword = findViewById(R.id.edtPassword);

        btnLogin = findViewById(R.id.btnLogin);
        btnReg = findViewById(R.id.btnReg);

        btnLogin.setOnClickListener(v -> {
            try{
                String login = edtLogin.getText().toString();
                String password = edtPassword.getText().toString();

                if(login.length() == 0 || password.length() ==0){
                    Toast.makeText(this, "Заполните поля", Toast.LENGTH_SHORT).show();
                    return;
                }

                List<Client> clients = ApiClient.get(Client.class);

                for(Client client : clients){
                    if(client.getUser().getLogin().equals(login) && client.getUser().getPassword().equals(password)){

                        SingletonService.mainClient = client;

                        Intent intent = new Intent(this, MainActivity.class);
                        startActivity(intent);
                        return;
                    }
                }

                Toast.makeText(this, "Неверные логин или пароль", Toast.LENGTH_SHORT).show();

            }catch (Exception ex){
                Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        btnReg.setOnClickListener(v -> {
            Intent intent = new Intent(this, RegistrationActivity.class);
            startActivity(intent);
        });
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
    }
}