package com.example.edueats.ui.notifications;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.edueats.AuthActivity;
import com.example.edueats.R;
import com.example.edueats.databinding.FragmentNotificationsBinding;
import com.example.edueats.services.ApiClient;
import com.example.edueats.services.SingletonService;

public class NotificationsFragment extends Fragment {


    private EditText edtFirstName, edtSecondName, edtLastName, edtLogin, edtPassword;
    private Button btnSaveChange, btnLogOut, btnUpBalance;
    private TextView txtBalance;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_notifications, container, false);

        edtFirstName = v.findViewById(R.id.edtFirstName);
        edtSecondName = v.findViewById(R.id.edtSecondName);
        edtLastName = v.findViewById(R.id.edtLastName);
        edtLogin = v.findViewById(R.id.edtLogin);
        edtPassword = v.findViewById(R.id.edtPassword);
        btnSaveChange = v.findViewById(R.id.btnSaveChange);
        btnLogOut = v.findViewById(R.id.btnLogOut);
        btnUpBalance = v.findViewById(R.id.btnUpBalance);
        txtBalance = v.findViewById(R.id.txtBalance);

        edtFirstName.setText(SingletonService.mainClient.getUser().getFirstName());
        edtSecondName.setText(SingletonService.mainClient.getUser().getSecondName());
        edtLastName.setText(SingletonService.mainClient.getUser().getLastName());
        edtLogin.setText(SingletonService.mainClient.getUser().getLogin());
        edtPassword.setText(SingletonService.mainClient.getUser().getPassword());
        txtBalance.setText(String.valueOf(SingletonService.mainClient.getBalance()));

        btnLogOut.setOnClickListener(view -> {
            SingletonService.clear();
            Intent intent = new Intent(getContext(), AuthActivity.class);
            startActivity(intent);
        });

        btnSaveChange.setOnClickListener(view -> {
            String fn = edtFirstName.getText().toString();
            String sn = edtSecondName.getText().toString();
            String ln = edtLastName.getText().toString();
            String login = edtLogin.getText().toString();
            String password = edtPassword.getText().toString();

            SingletonService.mainClient.getUser().setFirstName(fn);
            SingletonService.mainClient.getUser().setSecondName(sn);
            SingletonService.mainClient.getUser().setLastName(ln);
            SingletonService.mainClient.getUser().setLogin(login);
            SingletonService.mainClient.getUser().setPassword(password);

            ApiClient.update(SingletonService.mainClient);

        });

        btnUpBalance.setOnClickListener(view -> {
            SingletonService.mainClient.upBalance(1000);
            txtBalance.setText(String.valueOf(SingletonService.mainClient.getBalance()));
            ApiClient.update(SingletonService.mainClient);
        });

        return v;
    }


}