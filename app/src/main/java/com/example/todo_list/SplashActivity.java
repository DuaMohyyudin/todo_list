package com.example.todo_list;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    private ActivityResultLauncher<Intent> startForResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Hide the status bar and navigation bar
        hideSystemUI();

        ImageView ivlogo = findViewById(R.id.logo);
        TextView tvlogoDescription = findViewById(R.id.logo_description);
        Animation animLogo = AnimationUtils.loadAnimation(this, R.anim.logo_animation);
        Animation animText = AnimationUtils.loadAnimation(this, R.anim.slogan_animation);

        // Initialize ActivityResultLauncher
        startForResult = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK) {
                        Intent data = result.getData();
                        if (data != null) {
                            String resultMessage = data.getStringExtra("resultMessage");
                            tvlogoDescription.setText(resultMessage);
                        }
                    }
                });

        // Start logo animation
        ivlogo.startAnimation(animLogo);

        animLogo.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}

            @Override
            public void onAnimationEnd(Animation animation) {
                tvlogoDescription.startAnimation(animText);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {}
        });

        animText.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}

            @Override
            public void onAnimationEnd(Animation animation) {
                // Start MainActivity for result
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startForResult.launch(intent); // Use the launcher to start MainActivity
            }

            @Override
            public void onAnimationRepeat(Animation animation) {}
        });
    }

    private void hideSystemUI() {
        // Enables regular immersive mode
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_FULLSCREEN // Hides the status bar
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // Hides the navigation bar
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE // Ensures layout stability
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION // Layouts to hide navigation
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN // Layouts to hide status bar
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY // Sticky immersive mode
        );
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            hideSystemUI(); // Re-hide UI when the window gains focus again
        }
    }
}
