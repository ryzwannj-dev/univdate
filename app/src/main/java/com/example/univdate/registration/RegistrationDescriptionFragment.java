package com.example.univdate.registration;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.univdate.R;

public class RegistrationDescriptionFragment extends Fragment {

    private EditText editDescription, editInterests;
    private TextView charCountDescription, charCountInterests;
    private Button buttonContinue;
    private ImageView back;

    private static final int MAX_CHAR = 300;

    public RegistrationDescriptionFragment() {}

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        requireActivity().getOnBackPressedDispatcher().addCallback(
                getViewLifecycleOwner(),
                new OnBackPressedCallback(true) {
                    @Override
                    public void handleOnBackPressed() {}
                }
        );
        return inflater.inflate(R.layout.fragment_registration_description, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        editDescription = view.findViewById(R.id.edit_description);
        editInterests = view.findViewById(R.id.edit_interests);
        charCountDescription = view.findViewById(R.id.char_count_description);
        charCountInterests = view.findViewById(R.id.char_count_interests);
        buttonContinue = view.findViewById(R.id.button_continue);
        back = view.findViewById(R.id.back_button);

        updateCharCount(charCountDescription, editDescription.getText().length());
        updateCharCount(charCountInterests, editInterests.getText().length());

        editDescription.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {
                updateCharCount(charCountDescription, s.length());
            }
            @Override public void afterTextChanged(Editable s) {}
        });

        editInterests.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {
                updateCharCount(charCountInterests, s.length());
            }
            @Override public void afterTextChanged(Editable s) {}
        });

        buttonContinue.setOnClickListener(v -> {
            // Logique de navigation ici
        });

        back.setOnClickListener(v -> {
            if (getActivity() instanceof RegistrationActivity) {
                ((RegistrationActivity) getActivity()).decrementProgressBy(20);
            }
            FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container_view, new RegistrationProfileSecondFragment());
            transaction.addToBackStack(null);
            transaction.commit();
        });
    }

    private void updateCharCount(TextView textView, int currentLength) {
        textView.setText(currentLength + " / " + MAX_CHAR);
    }
}
