package com.example.ccdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.ccdemo.Model.UserLogin;

public class ManagerLoginActivity extends AppCompatActivity {

    private ManagerLoginVM viewModel;
    private EditText edtUsername, edtPassword;
    private Button btnLogin;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_login);

        viewModel = new ViewModelProvider(this).get(ManagerLoginVM.class);

        edtUsername = findViewById(R.id.edt_username_manager_login);
        edtPassword = findViewById(R.id.edt_password_manager_login);
        btnLogin = findViewById(R.id.btn_login_manager_login);
        progressBar = findViewById(R.id.progressBar);

        final String[] userType = new String[1];

        btnLogin.setOnClickListener(v -> {
            progressBar.setVisibility(View.VISIBLE); // Show the ProgressBar
            String username = edtUsername.getText().toString();
            String password = edtPassword.getText().toString();
            UserLogin userLogin = new UserLogin(username, password);
            userType[0] = viewModel.validateLogin(userLogin);
            progressBar.setVisibility(View.GONE);
            if (userType[0] == null)
                warnUser();
            else
                navigateToMainPage(userType[0]);
        });
    }

    private void navigateToMainPage(String userType) {
        Log.d("MANLOGANCT", "navigateToMainPage: ");
    }

    private void warnUser() {
        Toast.makeText(this, "Invalid login, please try again.", Toast.LENGTH_LONG).show();
    }
}