package dev.netanelbcn.kinderkit.Views;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract;
import com.firebase.ui.auth.IdpResponse;
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.List;

import dev.netanelbcn.kinderkit.Models.Member;
import dev.netanelbcn.kinderkit.R;

public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth auth;
    private Member member = new Member();
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login();
    }

    private void login() {
        // Choose authentication providers
        List<AuthUI.IdpConfig> providers = Arrays.asList(
                new AuthUI.IdpConfig.EmailBuilder().build(),
                new AuthUI.IdpConfig.PhoneBuilder().build());
//                new AuthUI.IdpConfig.GoogleBuilder().build(),
//                new AuthUI.IdpConfig.FacebookBuilder().build(),
//                new AuthUI.IdpConfig.TwitterBuilder().build());

// Create and launch sign-in intent
        Intent signInIntent = AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .setTheme(R.style.LoginTheme)
                .build();
        signInLauncher.launch(signInIntent);
    }

    // See: https://developer.android.com/training/basics/intents/result
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
            Intent intent = new Intent(LoginActivity.this, MenuActivity.class);
           Uri photoUrl = user.getPhotoUrl();
            String name = user.getDisplayName();
            String email = user.getEmail();
            String phone = user.getPhoneNumber();
            if (photoUrl != null)
                intent.putExtra("uri", photoUrl.toString());
            if (name != null)
                intent.putExtra("name", name);
            if (email != null)
                intent.putExtra("email", email);
            if (phone != null)
                intent.putExtra("phone", phone);
            startActivity(intent);
        } else {
            Log.d("LogInError", "Sign in failed");
        }
    }
    private void buildMemberDetails(FirebaseUser user) {
        String photoUrlString = user.getPhotoUrl().toString();
        member.setEmail(user.getEmail())
                .setPhone(user.getPhoneNumber())
                .setName(user.getDisplayName())
                .setPhotoUri(photoUrlString);
        int x = 0;
    }


}


//package dev.netanelbcn.kinderkit.Views;
//
//import androidx.activity.result.ActivityResultCallback;
//import androidx.activity.result.ActivityResultLauncher;
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.appcompat.app.AppCompatDelegate;
//
//import android.content.Intent;
//import android.net.Uri;
//import android.os.Bundle;
//
//import com.firebase.ui.auth.AuthUI;
//import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract;
//import com.firebase.ui.auth.IdpResponse;
//import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult;
//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.Task;
//import com.google.firebase.Firebase;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseUser;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.Objects;
//
//import dev.netanelbcn.kinderkit.Models.Member;
//import dev.netanelbcn.kinderkit.R;
//import dev.netanelbcn.kinderkit.Uilities.AuthenticationManager;
//
//public class LoginActivity extends AppCompatActivity {
//    private FirebaseAuth auth;
//    private Member member = new Member();
//    FirebaseUser user;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
//        setContentView(R.layout.activity_login);
//        auth = FirebaseAuth.getInstance();
//        user = auth.getCurrentUser();
//        if (user == null)
//            AuthenticationManager.getInstance().logIn();
//        else {
//            buildMemberDetails(user);
//        }
//        Intent intent = new Intent(LoginActivity.this, MenuActivity.class);
//        startActivity(intent);
//        // Finish the current activity to prevent the user from going back to the login screen
//        finish();
//    }
//
////    private void logIn() {
////        // Choose authentication providers
////        List<AuthUI.IdpConfig> providers = Arrays.asList(
////                new AuthUI.IdpConfig.EmailBuilder().build(),
////                new AuthUI.IdpConfig.PhoneBuilder().build());
////// Create and launch sign-in intent
////        Intent signInIntent = AuthUI.getInstance()
////                .createSignInIntentBuilder()
////                .setAvailableProviders(providers)
////                .setTheme(R.style.LoginTheme)
////                .build();
//////        buildMemberDetails(user);
////
////    }
//
//    private void buildMemberDetails(FirebaseUser user) {
//        String photoUrlString = null;
//        Uri photoUrl = user.getPhotoUrl();
//        if (photoUrl != null) {
//            photoUrlString = photoUrl.toString();
//        }
//
//        member.setEmail(user.getEmail())
//                .setPhone(user.getPhoneNumber())
//                .setName(user.getDisplayName())
//                .setPhotoUri(photoUrlString);
//        int x=0;
//    }
//
//
////    private void logOut() {
////        AuthUI.getInstance()
////                .signOut(this)
////                .addOnCompleteListener(new OnCompleteListener<Void>() {
////                    public void onComplete(@NonNull Task<Void> task) {
////                        // ...
////                    }
////                });
////    }
//
////    private void deleteAccount() {
////        AuthUI.getInstance()
////                .delete(this)
////                .addOnCompleteListener(new OnCompleteListener<Void>() {
////                    public void onComplete(@NonNull Task<Void> task) {
////                        // ...
////                    }
////                });
////    }
//
//    // See: https://developer.android.com/training/basics/intents/result
//    private final ActivityResultLauncher<Intent> signInLauncher = registerForActivityResult(
//            new FirebaseAuthUIActivityResultContract(),
//            new ActivityResultCallback<FirebaseAuthUIAuthenticationResult>() {
//                @Override
//                public void onActivityResult(FirebaseAuthUIAuthenticationResult result) {
//                    AuthenticationManager.getInstance().onSignInResult(result);
//                }
//            }
//    );
//
////    private void onSignInResult(FirebaseAuthUIAuthenticationResult result) {
////        IdpResponse response = result.getIdpResponse();
////        if (result.getResultCode() == RESULT_OK) {
////            // Successfully signed in
////            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
////            // Finish the current activity to prevent the user from going back to the login screen
////            finish();
////            // ...
////        } else {
////            // Sign in failed. If response is null the user canceled the
////            // sign-in flow using the back button. Otherwise check
////            // response.getError().getErrorCode() and handle the error.
////            // ...
////        }
////    }
//
//}