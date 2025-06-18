package com.example.univdate.registration;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.univdate.R;

public class RegistrationCodeFragment extends Fragment {

    private EditText editValidationCode;
    private Button buttonValidate;
    private ImageView back;

    public RegistrationCodeFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        requireActivity().getOnBackPressedDispatcher().addCallback(
                getViewLifecycleOwner(),
                new OnBackPressedCallback(true) {
                    @Override
                    public void handleOnBackPressed() {}
                }
        );
        return inflater.inflate(R.layout.fragment_registration_code, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        editValidationCode = view.findViewById(R.id.edit_validation_code);
        buttonValidate = view.findViewById(R.id.button_validate);
        back = view.findViewById(R.id.back_button);

        buttonValidate.setOnClickListener(v -> {
            if (getActivity() instanceof RegistrationActivity) {
                ((RegistrationActivity) getActivity()).incrementProgressBy(20);
            }
            FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container_view, new RegistrationProfileFirstFragment());
            transaction.addToBackStack(null);
            transaction.commit();
        });

        back.setOnClickListener(v -> {
            if (getActivity() instanceof RegistrationActivity) {
                ((RegistrationActivity) getActivity()).decrementProgressBy(20);
            }
            FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container_view, new RegistrationFirstFragment());
            transaction.addToBackStack(null);
            transaction.commit();
        });
    }
}
