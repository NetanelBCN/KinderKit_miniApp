package dev.netanelbcn.kinderkit.Views;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract;
import com.firebase.ui.auth.IdpResponse;
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import dev.netanelbcn.kinderkit.Interfaces.DataLoadCallback;
import dev.netanelbcn.kinderkit.Models.Kid;
import dev.netanelbcn.kinderkit.R;
import dev.netanelbcn.kinderkit.Uilities.DataManager;
import dev.netanelbcn.kinderkit.Uilities.FBmanager;

public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth auth;
    private FirebaseUser user;
    private FBmanager fbManager;
    private DataManager manager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        manager = DataManager.getInstance();
        login();
    }
    private void login() {
        List<AuthUI.IdpConfig> providers = Arrays.asList(
                new AuthUI.IdpConfig.EmailBuilder().build(),
                new AuthUI.IdpConfig.PhoneBuilder().build());
        Intent signInIntent = AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .setTheme(R.style.LoginTheme)
                .build();
        signInLauncher.launch(signInIntent);
    }
    private final ActivityResultLauncher<Intent> signInLauncher = registerForActivityResult(
            new FirebaseAuthUIActivityResultContract(),
            new ActivityResultCallback<FirebaseAuthUIAuthenticationResult>() {
                @Override
                public void onActivityResult(FirebaseAuthUIAuthenticationResult result) {
                    onSignInResult(result);
                }
            }
    );
    private void onSignInResult(FirebaseAuthUIAuthenticationResult result) {
        IdpResponse response = result.getIdpResponse();
        if (result.getResultCode() == RESULT_OK) {
            // Successfully signed in
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            Uri photoUrl = user.getPhotoUrl();
            String name = user.getDisplayName();
            String email = user.getEmail();
            String phone = user.getPhoneNumber();
            manager.setUid(user);
            fbManager = new FBmanager(user);
//            manager.InitGeneralData();
            fbManager.LoadDataFromFBRTDB(() -> {
                manager.setKids(fbManager.getKids());
                manager.getKids().sort(Comparator.comparingInt(Kid::getAge).reversed());
                Intent intent = new Intent(LoginActivity.this, MenuActivity.class);
                if (photoUrl != null)
                    intent.putExtra("uri", photoUrl.toString());
                if (name != null)
                    intent.putExtra("name", name);
                if (email != null)
                    intent.putExtra("email", email);
                if (phone != null)
                    intent.putExtra("phone", phone);
                startActivity(intent);
                finish();
            });
//            Intent intent = new Intent(LoginActivity.this, MenuActivity.class);
//            if (photoUrl != null)
//                intent.putExtra("uri", photoUrl.toString());
//            if (name != null)
//                intent.putExtra("name", name);
//            if (email != null)
//                intent.putExtra("email", email);
//            if (phone != null)
//                intent.putExtra("phone", phone);
//            startActivity(intent);
//            finish();

        } else {
            Log.d("LogInError", "Sign in failed");
        }
    }
}