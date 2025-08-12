package com.example.univdate.registration;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.univdate.MainActivity;
import com.example.univdate.R;

public class SignupActivity extends AppCompatActivity {

    private EditText inputEmail, inputPassword, inputConfirmPassword;
    private Button btnNext;
    private ImageButton btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        // Initialiser les vues
        inputEmail = findViewById(R.id.input_email);
        inputPassword = findViewById(R.id.input_password);
        inputConfirmPassword = findViewById(R.id.input_confirm_password);
        btnNext = findViewById(R.id.btn_next);
        btnBack = findViewById(R.id.btn_back);

        // Gestion bouton "Retour"
        btnBack.setOnClickListener(v -> {
            Intent intent = new Intent(SignupActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });


        // Gestion bouton "Next"
        btnNext.setOnClickListener(v -> {
            // Vérifier si les champs sont vides
            if (inputEmail.getText().toString().isEmpty() ||
                    inputPassword.getText().toString().isEmpty() ||
                    inputConfirmPassword.getText().toString().isEmpty()) {
                Toast.makeText(SignupActivity.this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
            } else {
                // Vérifier si les mots de passe correspondent
                if (!inputPassword.getText().toString().equals(inputConfirmPassword.getText().toString())) {
                    Toast.makeText(SignupActivity.this, "Les mots de passe ne correspondent pas", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(SignupActivity.this, CodeConfActivity.class);
                    intent.putExtra("email", inputEmail.getText().toString());
                    intent.putExtra("password", inputPassword.getText().toString());
                    startActivity(intent);
                }
            }
        });
    }

}
