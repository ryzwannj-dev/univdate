package com.example.univdate.registration;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.univdate.ForgotPasswordActivity;
import com.example.univdate.MainActivity;
import com.example.univdate.R;

import java.io.IOException;

public class StatusVerificationFragment extends Fragment {

    private Button btnUploadStudentProof, btnUploadIdCard, btnSubmit;
    private ImageView imgStudentProofPreview, imgIdCardPreview;

    private Uri studentProofUri = null;
    private Uri idCardUri = null;

    private ActivityResultLauncher<String> studentProofPickerLauncher;
    private ActivityResultLauncher<String> idCardPickerLauncher;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_status_verification, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        btnUploadStudentProof = view.findViewById(R.id.btn_upload_student_proof);
        btnUploadIdCard = view.findViewById(R.id.btn_upload_id_card);
        btnSubmit = view.findViewById(R.id.btn_submit);

        imgStudentProofPreview = view.findViewById(R.id.img_student_proof_preview);
        imgIdCardPreview = view.findViewById(R.id.img_id_card_preview);

        Context context = requireContext();

        studentProofPickerLauncher = registerForActivityResult(
                new ActivityResultContracts.GetContent(),
                uri -> {
                    if (uri != null) {
                        studentProofUri = uri;
                        imgStudentProofPreview.setVisibility(View.VISIBLE);
                        try {
                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), uri);
                            imgStudentProofPreview.setImageBitmap(bitmap);
                        } catch (IOException e) {
                            e.printStackTrace();
                            Toast.makeText(context, "Erreur lors du chargement de l'image", Toast.LENGTH_SHORT).show();
                        }
                        Toast.makeText(context, "Preuve étudiante sélectionnée", Toast.LENGTH_SHORT).show();
                    }
                });

        idCardPickerLauncher = registerForActivityResult(
                new ActivityResultContracts.GetContent(),
                uri -> {
                    if (uri != null) {
                        idCardUri = uri;
                        imgIdCardPreview.setVisibility(View.VISIBLE);
                        try {
                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), uri);
                            imgIdCardPreview.setImageBitmap(bitmap);
                        } catch (IOException e) {
                            e.printStackTrace();
                            Toast.makeText(context, "Erreur lors du chargement de l'image", Toast.LENGTH_SHORT).show();
                        }
                        Toast.makeText(context, "Carte d'identité sélectionnée", Toast.LENGTH_SHORT).show();
                    }
                });

        btnUploadStudentProof.setOnClickListener(v -> studentProofPickerLauncher.launch("image/*"));

        btnUploadIdCard.setOnClickListener(v -> idCardPickerLauncher.launch("image/*"));

        btnSubmit.setOnClickListener(v -> {
            if (studentProofUri == null) {
                Toast.makeText(requireContext(), "Veuillez télécharger une preuve étudiante.", Toast.LENGTH_SHORT).show();
                return;
            }
            if (idCardUri == null) {
                Toast.makeText(requireContext(), "Veuillez télécharger votre carte d'identité.", Toast.LENGTH_SHORT).show();
                return;
            }

            // Exemple : ouvrir ForgotPasswordActivity
            Intent intent = new Intent(requireContext(), InfoSentActivity.class);
            startActivity(intent);

            // Si tu veux fermer l'activité parent après le lancement :
            // requireActivity().finish();
        });
    }


}
