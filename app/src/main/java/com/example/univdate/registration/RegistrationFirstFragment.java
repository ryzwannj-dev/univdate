package com.example.univdate.registration;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.univdate.R;
import com.example.univdate.login.LoginActivity;

public class RegistrationFirstFragment extends Fragment {

    private EditText editEmail, editPassword, editConfPassword;
    private TextView textTitle, textDescription, textNote;
    private ImageView submitButton, back;

    public RegistrationFirstFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        requireActivity().getOnBackPressedDispatcher().addCallback(
                getViewLifecycleOwner(),
                new OnBackPressedCallback(true) {
                    @Override
                    public void handleOnBackPressed() {}
                }
        );
        return inflater.inflate(R.layout.fragment_registration_first, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        editEmail = view.findViewById(R.id.edit_email2);
        editPassword = view.findViewById(R.id.edit_password);
        editConfPassword = view.findViewById(R.id.edit_conf_password);

        textTitle = view.findViewById(R.id.textView2);
        textNote = view.findViewById(R.id.textView3);
        textDescription = view.findViewById(R.id.textView4);

        submitButton = view.findViewById(R.id.imageView);
        back = view.findViewById(R.id.back_button);

        submitButton.setOnClickListener(v -> {
            if (getActivity() instanceof RegistrationActivity) {
                ((RegistrationActivity) getActivity()).incrementProgressBy(20);
            }
            FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container_view, new RegistrationCodeFragment());
            transaction.addToBackStack(null);
            transaction.commit();
        });

        back.setOnClickListener(v -> {
            Intent intent = new Intent(requireContext(), LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            requireActivity().finish();
        });
    }
}
