package com.example.univdate.app;

import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import com.example.univdate.R;

public class HomeActivity extends AppCompatActivity {

    private View selectedNavItem = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Récupération des boutons de navigation
        View navCards = findViewById(R.id.nav_cards);
        View navMessages = findViewById(R.id.nav_messages);
        View navUsers = findViewById(R.id.nav_users);
        View navMap = findViewById(R.id.nav_map);
        View navProfile = findViewById(R.id.nav_profile);
        View bottomNav = findViewById(R.id.bottom_nav);

        ViewCompat.setOnApplyWindowInsetsListener(bottomNav, (v, insets) -> {
            int bottomInset = insets.getInsets(WindowInsetsCompat.Type.navigationBars()).bottom;

            int minPaddingBottom = (int) TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP,
                    20,
                    v.getResources().getDisplayMetrics()
            );

            int paddingBottom = bottomInset + minPaddingBottom;

            v.setPadding(
                    v.getPaddingLeft(),
                    v.getPaddingTop(),
                    v.getPaddingRight(),
                    paddingBottom
            );

            return insets;
        });

        navCards.setOnClickListener(v -> selectNavItem(v, new CardsFragment()));
        navMessages.setOnClickListener(v -> selectNavItem(v, new MessagesFragment()));
        navUsers.setOnClickListener(v -> selectNavItem(v, new UsersFragment()));
        navMap.setOnClickListener(v -> selectNavItem(v, new MapFragment()));
        navProfile.setOnClickListener(v -> selectNavItem(v, new ProfileFragment()));

        // Sélection par défaut
        selectNavItem(navCards, new CardsFragment());
    }

    private void selectNavItem(View navItem, Fragment fragment) {
        // Si un item était déjà sélectionné, on remet son icône à la couleur par défaut
        if (selectedNavItem != null) {
            setIconColor(selectedNavItem, "#ab9db9");
        }

        // On met la nouvelle icône en blanc
        setIconColor(navItem, "#FFFFFF");

        // Mémorisation du nouvel item sélectionné
        selectedNavItem = navItem;

        // Chargement du fragment demandé
        loadFragment(fragment);
    }

    private void setIconColor(View navItem, String colorHex) {
        // Récupère l'ImageView enfant (icône) et applique la couleur
        if (navItem instanceof LinearLayout) {
            LinearLayout layout = (LinearLayout) navItem;
            // On suppose qu'il y a un seul ImageView enfant (l'icône)
            for (int i = 0; i < layout.getChildCount(); i++) {
                View child = layout.getChildAt(i);
                if (child instanceof ImageView) {
                    ((ImageView) child).setColorFilter(Color.parseColor(colorHex));
                    break;
                }
            }
        }
    }

    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_container, fragment)
                .commit();
    }
}
