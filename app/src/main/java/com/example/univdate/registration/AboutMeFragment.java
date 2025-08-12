package com.example.univdate.registration;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.univdate.R;
import com.google.android.flexbox.FlexboxLayout;

public class AboutMeFragment extends Fragment {

    private String[] interests = {
            "Sport", "Musique", "Voyages", "Cinéma", "Lecture", "Cuisine", "Art",
            "Technologie", "Mode", "Bénévolat", "Jeux vidéo", "Séries", "Animaux",
            "Nature", "Écriture", "Danse", "Théâtre", "Photographie", "Jardinage",
            "Sciences", "Histoire", "Politique", "Économie", "Spiritualité", "Méditation",
            "Développement personnel", "Sorties", "Soirées", "Festivals", "Concerts",
            "Musées", "Expositions", "Shopping", "Couture", "DIY", "Langues étrangères",
            "Apprentissage", "Bricolage", "Casse-têtes", "Jeux de société"
    };

    private EditText inputUniversity, inputMajor, inputAbout;
    private FlexboxLayout interestsContainer;
    private Button btnLogin;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_about_me, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        inputUniversity = view.findViewById(R.id.input_university);
        inputMajor = view.findViewById(R.id.input_major);
        inputAbout = view.findViewById(R.id.input_about);
        interestsContainer = view.findViewById(R.id.interests_container);
        btnLogin = view.findViewById(R.id.btnLogin);

        Context context = requireContext();

        for (String interest : interests) {
            TextView chip = new TextView(context);
            chip.setText(interest);
            chip.setTextColor(getResources().getColor(android.R.color.white));
            chip.setBackgroundResource(R.drawable.input_bg);

            int hPad = dpToPx(context, 16);
            int vPad = dpToPx(context, 6);
            chip.setPadding(hPad, vPad, hPad, vPad);

            FlexboxLayout.LayoutParams lp = new FlexboxLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            );
            int margin = dpToPx(context, 6);
            lp.setMargins(margin, margin, margin, margin);
            chip.setLayoutParams(lp);

            chip.setTag(false);

            chip.setOnClickListener(v -> {
                boolean selected = (boolean) chip.getTag();
                selected = !selected;
                chip.setTag(selected);

                if (selected) {
                    chip.setBackgroundResource(R.drawable.btn_purple);
                } else {
                    chip.setBackgroundResource(R.drawable.input_bg);
                }
            });

            interestsContainer.addView(chip);
        }

        btnLogin.setOnClickListener(v -> {
            String university = inputUniversity.getText().toString().trim();
            String major = inputMajor.getText().toString().trim();
            String about = inputAbout.getText().toString().trim();

            if (university.isEmpty() || major.isEmpty() || about.isEmpty()) {
                Toast.makeText(context, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
                return;
            }

            // Récupérer les intérêts sélectionnés
            StringBuilder selectedInterests = new StringBuilder();
            for (int i = 0; i < interestsContainer.getChildCount(); i++) {
                View child = interestsContainer.getChildAt(i);
                if (child instanceof TextView) {
                    TextView chip = (TextView) child;
                    boolean selected = (boolean) chip.getTag();
                    if (selected) {
                        if (selectedInterests.length() > 0) selectedInterests.append(", ");
                        selectedInterests.append(chip.getText());
                        ProfileActivity.updateDots(3);
                        goToVerificationStatusFragment();
                    }
                }
            }

            if (selectedInterests.length() == 0) {
                Toast.makeText(context, "Veuillez sélectionner au moins un intérêt", Toast.LENGTH_SHORT).show();
                return;
            }

            // Exemple d’action : afficher les données dans un Toast
            String message = "Université : " + university + "\n" +
                    "Filière : " + major + "\n" +
                    "À propos : " + about + "\n" +
                    "Intérêts : " + selectedInterests.toString();

            Toast.makeText(context, message, Toast.LENGTH_LONG).show();

            // Ici, tu peux faire ce que tu veux avec les données (envoyer au serveur, navigation, etc.)
        });
    }

    private int dpToPx(Context context, int dp) {
        return Math.round(dp * context.getResources().getDisplayMetrics().density);
    }

    private void goToVerificationStatusFragment() {
        StatusVerificationFragment fragment = new StatusVerificationFragment();
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.frameContent, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
