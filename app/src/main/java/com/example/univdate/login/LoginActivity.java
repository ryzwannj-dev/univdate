package com.example.univdate.login;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.univdate.R;
import com.example.univdate.registration.RegistrationActivity;

public class LoginActivity extends AppCompatActivity {
    private TextView textviewInfo;
    private EditText editEmail;
    private EditText editPassword;
    private Button buttonValidate;
    private TextView textviewForgot;
    private TextView textviewRegistration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        textviewInfo = findViewById(R.id.textview_info);
        editEmail = findViewById(R.id.edit_email);
        editPassword = findViewById(R.id.edit_password);
        buttonValidate = findViewById(R.id.button_validate);
        textviewForgot = findViewById(R.id.textview_forgot);
        textviewRegistration = findViewById(R.id.textview_registration);

        buttonValidate.setOnClickListener(v -> {
            String email = editEmail.getText().toString().trim();
            String password = editPassword.getText().toString().trim();

            if(email.isEmpty() || password.isEmpty()){

            }
        });

        textviewForgot.setOnClickListener(v -> {

        });

        textviewRegistration.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
            startActivity(intent);
        });
    }
}
