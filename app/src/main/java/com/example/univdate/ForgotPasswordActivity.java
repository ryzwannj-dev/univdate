package com.example.univdate;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ForgotPasswordActivity extends AppCompatActivity {

    private ImageButton btnBack;
    private EditText etEmail;
    private Button btnSent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        // Récupération des vues
        btnBack = findViewById(R.id.btn_back);
        etEmail = findViewById(R.id.et_email);
        btnSent = findViewById(R.id.btn_sent);

        // Handler bouton retour
        btnBack.setOnClickListener(v -> {
            finish(); // Ferme l'activité
        });

        // Handler bouton "Continue"
        btnSent.setOnClickListener(v -> {
            String email = etEmail.getText().toString().trim();

            if (TextUtils.isEmpty(email)) {
                Toast.makeText(this, "Veuillez entrer votre email", Toast.LENGTH_SHORT).show();
                return;
            }

            // TODO: Appeler ton API de reset password ici
            Toast.makeText(this, "Lien de réinitialisation envoyé à : " + email, Toast.LENGTH_LONG).show();

            // Exemple : revenir à l'écran de connexion après l'envoi
            Intent intent = new Intent(ForgotPasswordActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
