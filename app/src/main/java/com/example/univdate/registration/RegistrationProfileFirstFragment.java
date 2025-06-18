package com.example.univdate.registration;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.univdate.R;

public class RegistrationProfileFirstFragment extends Fragment {

    private EditText editPseudo;
    private Spinner spinnerAge, spinnerGenre;
    private RadioGroup radioGroupNeeds;
    private RadioButton radioLove, radioFeeling, radioFriend;
    private CheckBox checkBoxMen, checkBoxWomen;
    private Button buttonContinue;
    private ImageView back;

    public RegistrationProfileFirstFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        requireActivity().getOnBackPressedDispatcher().addCallback(
                getViewLifecycleOwner(),
                new OnBackPressedCallback(true) {
                    @Override
                    public void handleOnBackPressed() {}
                }
        );
        return inflater.inflate(R.layout.fragment_registration_profile_first, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        editPseudo = view.findViewById(R.id.edit_pseudo);
        spinnerAge = view.findViewById(R.id.spinner_age);
        spinnerGenre = view.findViewById(R.id.spinner_genre);

        radioGroupNeeds = view.findViewById(R.id.RGroup_needs);
        radioLove = view.findViewById(R.id.radio_love);
        radioFeeling = view.findViewById(R.id.radio_feeling);
        radioFriend = view.findViewById(R.id.radio_friend);

        checkBoxMen = view.findViewById(R.id.checkBox_men);
        checkBoxWomen = view.findViewById(R.id.checkBox_women);

        buttonContinue = view.findViewById(R.id.button_continue);
        back = view.findViewById(R.id.back_button);

        buttonContinue.setOnClickListener(v -> {
            if (getActivity() instanceof RegistrationActivity) {
                ((RegistrationActivity) getActivity()).incrementProgressBy(20);
            }
            FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container_view, new RegistrationProfileSecondFragment());
            transaction.addToBackStack(null);
            transaction.commit();
        });

        back.setOnClickListener(v -> {
            if (getActivity() instanceof RegistrationActivity) {
                ((RegistrationActivity) getActivity()).decrementProgressBy(20);
            }
            FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container_view, new RegistrationCodeFragment());
            transaction.addToBackStack(null);
            transaction.commit();
        });
    }
}
