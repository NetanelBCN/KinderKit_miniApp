package dev.netanelbcn.kinderkit.Views;

import android.net.Uri;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.AppCompatEditText;

import com.google.android.material.button.MaterialButton;

import dev.netanelbcn.kinderkit.Models.Kid;
import dev.netanelbcn.kinderkit.R;
import dev.netanelbcn.kinderkit.Uilities.DataManager;

public class AddKidActivity extends AppCompatActivity {

    private AppCompatEditText AK_ACET_editTextFirstName;
    private AppCompatEditText AK_ACET_editTextLastName;
    private DatePicker AK_DP_datePicker;
    private MaterialButton AK_MB_buttonAddPhoto;
    private MaterialButton AK_MB_buttonAddKid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_add_kid);
        connectUI();
        attachListeners();
    }

    private void attachListeners() {

        AK_MB_buttonAddKid.setOnClickListener(v -> {
            String firstName = AK_ACET_editTextFirstName.getText().toString();
            String lastName = AK_ACET_editTextLastName.getText().toString();
            if (!(firstName.isEmpty() || lastName.isEmpty())) {
                int day = AK_DP_datePicker.getDayOfMonth();
                int month = AK_DP_datePicker.getMonth();
                int year = AK_DP_datePicker.getYear();
                Kid kid = new Kid().setBirthDate(day, month + 1, year).setfName(firstName).setlName(lastName);
               // kid.setProfilePhotoUri(Uri.parse("https://firebasestorage.googleapis.com/v0/b/kinderkit-68d4c.appspot.com/o/Ilay.jpg?alt=media&token=3f7ea009-c34d-4692-b015-9b08fc5468a3"));
                DataManager.getInstance().addKid(kid);
            } else
                Toast.makeText(this, "Can't create Kid with missing fields", Toast.LENGTH_SHORT).show();
            finish();
        });
    }

    private void connectUI() {
        AK_ACET_editTextFirstName = findViewById(R.id.AK_ACET_editTextFirstName);
        AK_ACET_editTextLastName = findViewById(R.id.AK_ACET_editTextLastName);
        AK_DP_datePicker = findViewById(R.id.AK_DP_datePicker);
        AK_MB_buttonAddPhoto = findViewById(R.id.AK_MB_buttonAddPhoto);
        AK_MB_buttonAddKid = findViewById(R.id.AK_MB_buttonAddKid);
    }
}