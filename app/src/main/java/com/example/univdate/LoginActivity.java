package com.example.univdate;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.univdate.app.HomeActivity;
import com.example.univdate.registration.SignupActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText etUsername, etPassword;
    private Button btnLogin, btnSignup;
    private TextView tvForgotPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialisation des vues
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnSignup = findViewById(R.id.btnSignup);
        tvForgotPassword = findViewById(R.id.tvForgotPassword);

        ImageButton btnBack = findViewById(R.id.btn_back);
        btnBack.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });


        // Handler du bouton "Log in"
        btnLogin.setOnClickListener(v -> {
            String username = etUsername.getText().toString().trim();
            String password = etPassword.getText().toString().trim();

            if (TextUtils.isEmpty(username)) {
                Toast.makeText(this, "Veuillez entrer votre nom d'utilisateur", Toast.LENGTH_SHORT).show();
                return;
            }
            if (TextUtils.isEmpty(password)) {
                Toast.makeText(this, "Veuillez entrer votre mot de passe", Toast.LENGTH_SHORT).show();
                return;
            }

            // TODO: Appeler ton API de login ici
            Toast.makeText(this, "Connexion réussie pour : " + username, Toast.LENGTH_SHORT).show();

            // Exemple : rediriger vers l'activité principale
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
            finish();
        });

        // Handler du bouton "Sign up"
        btnSignup.setOnClickListener(v -> {
            Intent intent = new Intent(this, SignupActivity.class);
            startActivity(intent);
        });

        // Handler du texte "Forgot password?"
        tvForgotPassword.setOnClickListener(v -> {
            Intent intent = new Intent(this, ForgotPasswordActivity.class);
            startActivity(intent);
        });
    }
}
