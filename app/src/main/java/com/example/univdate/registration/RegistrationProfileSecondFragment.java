package com.example.univdate.registration;

import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.univdate.R;

public class RegistrationProfileSecondFragment extends Fragment {

    private static final int MAX_CHAR = 300;

    private EditText editDescription, editInterests;
    private TextView descriptionCounter, interestsCounter;
    private Button buttonContinue;
    private ImageView back;
    private NestedScrollView nestedScrollView;

    public RegistrationProfileSecondFragment() {}

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_registration_profile_second, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        nestedScrollView = view.findViewById(R.id.nestedScrollView);
        editDescription = view.findViewById(R.id.editTextDescription);
        editInterests = view.findViewById(R.id.editTextInterests);
        descriptionCounter = view.findViewById(R.id.descriptionCharCounter);
        interestsCounter = view.findViewById(R.id.interestsCharCounter);
        buttonContinue = view.findViewById(R.id.button_validate);
        back = view.findViewById(R.id.back_button);

        setupCharCounter(editDescription, descriptionCounter);
        setupCharCounter(editInterests, interestsCounter);

        // Scroll fluide quand la description reçoit le focus
        editDescription.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                new Handler().postDelayed(() -> smoothScrollToY(0), 100);
            }
        });

        // Scroll fluide pour centrer l'input des intérêts
        editInterests.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                new Handler().postDelayed(() -> smoothScrollToY(editInterests.getTop()), 100);
            }
        });

        // Bouton "Valider"
        buttonContinue.setOnClickListener(v -> {
            if (getActivity() instanceof RegistrationActivity) {
                ((RegistrationActivity) getActivity()).incrementProgressBy(20);
            }
            FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container_view, new RegistrationDocVerifFragment());
            transaction.addToBackStack(null);
            transaction.commit();
        });

        // Bouton "Retour"
        back.setOnClickListener(v -> {
            if (getActivity() instanceof RegistrationActivity) {
                ((RegistrationActivity) getActivity()).decrementProgressBy(20);
            }
            FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container_view, new RegistrationProfileFirstFragment());
            transaction.addToBackStack(null);
            transaction.commit();
        });
    }

    private void setupCharCounter(EditText editText, TextView counterView) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {
                int length = s.length();
                counterView.setText(length + "/" + MAX_CHAR);

                if (length >= MAX_CHAR) {
                    counterView.setTextColor(Color.RED);
                } else {
                    counterView.setTextColor(getResources().getColor(R.color.light_grey));
                }
            }

            @Override public void afterTextChanged(Editable s) {}
        });
    }

    private void smoothScrollToY(int targetY) {
        ObjectAnimator animator = ObjectAnimator.ofInt(nestedScrollView, "scrollY", nestedScrollView.getScrollY(), targetY);
        animator.setDuration(400); // Durée du scroll en ms
        animator.setInterpolator(new DecelerateInterpolator());
        animator.start();
    }
}
