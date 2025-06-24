package com.example.univdate.registration;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.univdate.R;

public class RegistrationDocVerifFragment extends Fragment {

    private ImageView docUpload;
    private Button buttonContinue;
    private Uri selectedImageUri;

    // ✅ Nouveau launcher
    private final ActivityResultLauncher<Intent> imagePickerLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == getActivity().RESULT_OK && result.getData() != null) {
                    selectedImageUri = result.getData().getData();
                    if (selectedImageUri != null) {
                        docUpload.setImageURI(null);
                        docUpload.setImageURI(selectedImageUri);
                    } else {
                        Toast.makeText(getContext(), "Erreur lors du chargement de l'image", Toast.LENGTH_SHORT).show();
                    }
                }
            });

    public RegistrationDocVerifFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_registration_doc_verif, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        docUpload = view.findViewById(R.id.doc_upload);
        buttonContinue = view.findViewById(R.id.button_continue);

        docUpload.setOnClickListener(v -> openGallery());

        buttonContinue.setOnClickListener(v -> {
            if (selectedImageUri != null) {
                Toast.makeText(getContext(), "Document sélectionné", Toast.LENGTH_SHORT).show();
                // TODO : Traiter ou envoyer l'image ici
            } else {
                Toast.makeText(getContext(), "Veuillez ajouter un document", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        imagePickerLauncher.launch(intent);
    }
}
