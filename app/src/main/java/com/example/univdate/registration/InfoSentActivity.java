package com.example.univdate.registration;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.univdate.LoginActivity;
import com.example.univdate.MainActivity;
import com.example.univdate.R;

public class InfoSentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_info_sent);

        // Bouton "croix" fermer
        ImageButton btnClose = findViewById(R.id.btn_close);
        btnClose.setOnClickListener(v -> {
            Intent intent = new Intent(InfoSentActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });

        // Bouton "Retourner à l'accueil"
        Button btnSubmit = findViewById(R.id.btn_submit);
        btnSubmit.setOnClickListener(v -> {
            Intent intent = new Intent(InfoSentActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
