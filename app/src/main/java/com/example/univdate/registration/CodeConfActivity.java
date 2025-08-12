package com.example.univdate.registration;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.univdate.LoginActivity;
import com.example.univdate.MainActivity;
import com.example.univdate.R;

public class CodeConfActivity extends AppCompatActivity {

    private ImageView btnHelp;
    private EditText code1, code2, code3, code4, code5, code6;
    private TextView tvResend;
    private Button btnContinue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code_conf);

        // Récupération des vues
        btnHelp = findViewById(R.id.btn_help);
        code1 = findViewById(R.id.code1);
        code2 = findViewById(R.id.code2);
        code3 = findViewById(R.id.code3);
        code4 = findViewById(R.id.code4);
        code5 = findViewById(R.id.code5);
        code6 = findViewById(R.id.code6);
        tvResend = findViewById(R.id.tv_resend);
        btnContinue = findViewById(R.id.btn_continue);

        // Listener sur le bouton aide (exemple simple)
        btnHelp.setOnClickListener(v ->
                Toast.makeText(CodeConfActivity.this, "Aide sélectionnée", Toast.LENGTH_SHORT).show()
        );

        // Listener sur le bouton "Resend code"
        tvResend.setOnClickListener(v -> {
            // Logique pour renvoyer le code
            Toast.makeText(CodeConfActivity.this, "Code renvoyé", Toast.LENGTH_SHORT).show();
        });

        // Listener sur le bouton continuer
        btnContinue.setOnClickListener(v -> {
            String code = getCode();
            if (code.length() == 6) {
                startActivity(new Intent(CodeConfActivity.this, ProfileActivity.class));
            } else {
                Toast.makeText(CodeConfActivity.this, "Veuillez entrer un code à 6 chiffres", Toast.LENGTH_SHORT).show();
            }
        });

        // Pour avancer automatiquement le focus quand on tape un chiffre dans chaque EditText
        setupCodeInputs();
    }

    // Méthode pour concaténer le code entré
    private String getCode() {
        return code1.getText().toString().trim() +
                code2.getText().toString().trim() +
                code3.getText().toString().trim() +
                code4.getText().toString().trim() +
                code5.getText().toString().trim() +
                code6.getText().toString().trim();
    }

    private void setupCodeInputs() {
        setupNextFocus(code1, code2);
        setupNextFocus(code2, code3);
        setupNextFocus(code3, code4);
        setupNextFocus(code4, code5);
        setupNextFocus(code5, code6);
    }

    private void setupNextFocus(EditText current, EditText next) {
        current.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Rien
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 1) {
                    next.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Rien
            }
        });
    }
}
