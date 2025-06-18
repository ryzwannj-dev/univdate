package com.example.univdate.registration;
import android.animation.ObjectAnimator;
import android.view.animation.DecelerateInterpolator;


import android.os.Bundle;
import android.widget.Button;
import android.widget.ProgressBar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.univdate.R;

public class RegistrationActivity extends AppCompatActivity {
    public ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registration);

        progressBar = findViewById(R.id.progressBar);
        progressBar.setMax(100);
        progressBar.setProgress(0);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container_view, new RegistrationFirstFragment())
                    .commit();
        }
    }

    public void incrementProgressBy(int increment) {
        int currentProgress = progressBar.getProgress();
        int targetProgress = currentProgress + increment;
        if (targetProgress > progressBar.getMax()) {
            targetProgress = progressBar.getMax();
        }
        ObjectAnimator progressAnimator = ObjectAnimator.ofInt(progressBar, "progress", currentProgress, targetProgress);
        progressAnimator.setDuration(300); // durée en ms
        progressAnimator.setInterpolator(new DecelerateInterpolator());
        progressAnimator.start();
    }

    public void decrementProgressBy(int decrement) {
        int currentProgress = progressBar.getProgress();
        int targetProgress = currentProgress - decrement;
        if (targetProgress < 0) {
            targetProgress = 0;
        }
        ObjectAnimator progressAnimator = ObjectAnimator.ofInt(progressBar, "progress", currentProgress, targetProgress);
        progressAnimator.setDuration(300); // durée en ms
        progressAnimator.setInterpolator(new DecelerateInterpolator());
        progressAnimator.start();
    }

}