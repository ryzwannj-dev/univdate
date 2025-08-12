package com.example.univdate.registration;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;
import androidx.fragment.app.FragmentTransaction;

import com.example.univdate.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;

public class ProfileActivity extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;

    private ImageView backArrow, profilePhoto;
    private TextView title;
    public static View dot1, dot2, dot3;
    private EditText pseudoInput, birthdayInput, locationInput;
    private Spinner relationshipTypeSpinner, genderPreferenceSpinner, iamASpinner;
    private Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        initViews();
        setupSpinners();
        setupBirthdayInputFormatter();
        setupListeners();
    }

    private void initViews() {
        backArrow = findViewById(R.id.backArrow);
        title = findViewById(R.id.title);

        dot1 = findViewById(R.id.dot1);
        dot2 = findViewById(R.id.dot2);
        dot3 = findViewById(R.id.dot3);

        profilePhoto = findViewById(R.id.profilePhoto);

        pseudoInput = findViewById(R.id.pseudoInput);
        birthdayInput = findViewById(R.id.birthdayInput);
        locationInput = findViewById(R.id.locationInput);

        relationshipTypeSpinner = findViewById(R.id.relationshipTypeSpinner);
        genderPreferenceSpinner = findViewById(R.id.genderPreferenceSpinner);
        iamASpinner = findViewById(R.id.iamASpinner);

        btnNext = findViewById(R.id.btn_next);
    }

    private void setupSpinners() {
        final int selectedTextColor = 0xFFab9db9;

        ArrayAdapter<CharSequence> relationshipAdapter = new ArrayAdapter<CharSequence>(
                this, R.layout.spinner_item, getResources().getTextArray(R.array.relationship_types)) {
            @Override
            public View getView(int position, View convertView, android.view.ViewGroup parent) {
                TextView view = (TextView) super.getView(position, convertView, parent);
                view.setTextColor(position == 0 ? selectedTextColor : getContext().getResources().getColor(android.R.color.white));
                return view;
            }
            @Override
            public View getDropDownView(int position, View convertView, android.view.ViewGroup parent) {
                TextView view = (TextView) super.getDropDownView(position, convertView, parent);
                view.setTextColor(getContext().getResources().getColor(android.R.color.white));
                return view;
            }
        };
        relationshipAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        relationshipTypeSpinner.setAdapter(relationshipAdapter);

        ArrayAdapter<CharSequence> genderAdapter = new ArrayAdapter<CharSequence>(
                this, R.layout.spinner_item, getResources().getTextArray(R.array.gender_preferences)) {
            @Override
            public View getView(int position, View convertView, android.view.ViewGroup parent) {
                TextView view = (TextView) super.getView(position, convertView, parent);
                view.setTextColor(position == 0 ? selectedTextColor : getContext().getResources().getColor(android.R.color.white));
                return view;
            }
            @Override
            public View getDropDownView(int position, View convertView, android.view.ViewGroup parent) {
                TextView view = (TextView) super.getDropDownView(position, convertView, parent);
                view.setTextColor(getContext().getResources().getColor(android.R.color.white));
                return view;
            }
        };
        genderAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        genderPreferenceSpinner.setAdapter(genderAdapter);

        ArrayAdapter<CharSequence> iamAAdapter = new ArrayAdapter<CharSequence>(
                this, R.layout.spinner_item, getResources().getTextArray(R.array.iam_gender)) {
            @Override
            public View getView(int position, View convertView, android.view.ViewGroup parent) {
                TextView view = (TextView) super.getView(position, convertView, parent);
                view.setTextColor(position == 0 ? selectedTextColor : getContext().getResources().getColor(android.R.color.white));
                return view;
            }
            @Override
            public View getDropDownView(int position, View convertView, android.view.ViewGroup parent) {
                TextView view = (TextView) super.getDropDownView(position, convertView, parent);
                view.setTextColor(getContext().getResources().getColor(android.R.color.white));
                return view;
            }
        };
        iamAAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        iamASpinner.setAdapter(iamAAdapter);
    }

    private void setupListeners() {
        backArrow.setOnClickListener(v -> finish());

        profilePhoto.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            startActivityForResult(intent, PICK_IMAGE_REQUEST);
        });

        btnNext.setOnClickListener(v -> {
            if (validateInputs()) {
                updateDots(2);
                goToAboutMeFragment();
            }
        });
    }

    private void setupBirthdayInputFormatter() {
        birthdayInput.addTextChangedListener(new TextWatcher() {
            private String current = "";
            private final Calendar cal = Calendar.getInstance();

            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                String input = s.toString();
                if (input.isEmpty()) {
                    current = "";
                    birthdayInput.removeTextChangedListener(this);
                    birthdayInput.setText("");
                    birthdayInput.addTextChangedListener(this);
                    return;
                }
                if (!input.equals(current)) {
                    String clean = input.replaceAll("[^\\d]", "");
                    String cleanC = current.replaceAll("[^\\d]", "");
                    int cl = clean.length();
                    int sel = cl;
                    for (int i = 2; i <= cl && i < 6; i += 2) sel++;
                    if (clean.equals(cleanC)) sel--;
                    if (clean.length() < 8) {
                        StringBuilder formatted = new StringBuilder();
                        for (int i = 0; i < clean.length(); i++) {
                            formatted.append(clean.charAt(i));
                            if (i == 1 || i == 3) formatted.append('/');
                        }
                        current = formatted.toString();
                        birthdayInput.removeTextChangedListener(this);
                        birthdayInput.setText(current);
                        birthdayInput.setSelection(sel < current.length() ? sel : current.length());
                        birthdayInput.addTextChangedListener(this);
                        return;
                    }
                    int day = Integer.parseInt(clean.substring(0, 2));
                    int mon = Integer.parseInt(clean.substring(2, 4));
                    int year = Integer.parseInt(clean.substring(4, 8));
                    mon = mon < 1 ? 1 : Math.min(mon, 12);
                    cal.set(Calendar.MONTH, mon - 1);
                    year = (year < 1900) ? 1900 : Math.min(year, 2100);
                    cal.set(Calendar.YEAR, year);
                    day = Math.min(day, cal.getActualMaximum(Calendar.DATE));
                    clean = String.format("%02d%02d%04d", day, mon, year);
                    current = clean.substring(0, 2) + "/" + clean.substring(2, 4) + "/" + clean.substring(4, 8);
                    birthdayInput.removeTextChangedListener(this);
                    birthdayInput.setText(current);
                    birthdayInput.setSelection(sel < current.length() ? sel : current.length());
                    birthdayInput.addTextChangedListener(this);
                }
            }
        });
    }

    private boolean validateInputs() {
        if (TextUtils.isEmpty(pseudoInput.getText().toString().trim()) ||
                TextUtils.isEmpty(birthdayInput.getText().toString().trim()) ||
                TextUtils.isEmpty(locationInput.getText().toString().trim())) {
            Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public static void updateDots(int step) {
        dot1.setBackgroundResource(step >= 1 ? R.drawable.circle_white : R.drawable.circle_dimmed);
        dot2.setBackgroundResource(step >= 2 ? R.drawable.circle_white : R.drawable.circle_dimmed);
        dot3.setBackgroundResource(step >= 3 ? R.drawable.circle_white : R.drawable.circle_dimmed);
    }

    private void goToAboutMeFragment() {
        AboutMeFragment fragment = new AboutMeFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frameContent, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri imageUri = data.getData();

            try {
                // Récupérer bitmap depuis URI
                Bitmap originalBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);

                // Récupérer la taille minimale pour faire un carré
                int dimension = Math.min(originalBitmap.getWidth(), originalBitmap.getHeight());

                // Recadrer le bitmap en carré centré
                Bitmap squareBitmap = Bitmap.createBitmap(originalBitmap,
                        (originalBitmap.getWidth() - dimension) / 2,
                        (originalBitmap.getHeight() - dimension) / 2,
                        dimension,
                        dimension);

                // Créer un RoundedBitmapDrawable circulaire
                RoundedBitmapDrawable roundedDrawable = RoundedBitmapDrawableFactory.create(getResources(), squareBitmap);
                roundedDrawable.setCircular(true);

                // Appliquer à l'ImageView
                profilePhoto.setImageDrawable(roundedDrawable);

            } catch (IOException e) {
                e.printStackTrace();
                // En cas d'erreur, afficher directement l'image URI
                profilePhoto.setImageURI(imageUri);
            }
        }
    }

}
