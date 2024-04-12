package dev.netanelbcn.kinderkit.Views;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.AppCompatEditText;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.FirebaseApp;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.UUID;

import dev.netanelbcn.kinderkit.Models.Kid;
import dev.netanelbcn.kinderkit.Models.MyPhoto;
import dev.netanelbcn.kinderkit.R;
import dev.netanelbcn.kinderkit.Uilities.DataManager;

public class AddKidActivity extends AppCompatActivity {

    private AppCompatEditText AK_ACET_editTextFirstName;
    private AppCompatEditText AK_ACET_editTextLastName;
    private DatePicker AK_DP_datePicker;
    private MaterialButton AK_MB_buttonAddPhoto;
    private MaterialButton AK_MB_buttonAddKid;
    private StorageReference storageReference;
    private Uri image;
    private Uri fbImage;
    private final ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult o) {
            if (o.getResultCode() == RESULT_OK) {
                if (o.getData() != null) {
                    image = o.getData().getData();
                    Toast.makeText(AddKidActivity.this, "Image selected successfully", Toast.LENGTH_SHORT).show();
                    uploadImage(image); // Move uploadImage() call here
                } else {
                    Toast.makeText(AddKidActivity.this, "No image selected", Toast.LENGTH_SHORT).show();
                }
            }
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_add_kid);
        connectUI();
        FirebaseApp.initializeApp(AddKidActivity.this);
        storageReference = FirebaseStorage.getInstance().getReference();

        attachListeners();
    }

    private void attachListeners() {
        AK_MB_buttonAddPhoto.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            activityResultLauncher.launch(intent);
        });
        AK_MB_buttonAddKid.setOnClickListener(v -> {
            String firstName = AK_ACET_editTextFirstName.getText().toString();
            String lastName = AK_ACET_editTextLastName.getText().toString();
            if (!(firstName.isEmpty() || lastName.isEmpty())) {
                int day = AK_DP_datePicker.getDayOfMonth();
                int month = AK_DP_datePicker.getMonth();
                int year = AK_DP_datePicker.getYear();
                Kid kid = new Kid().setBirthDate(day, month + 1, year).setfName(firstName).setlName(lastName);
                if (fbImage != null) {
                    kid.setProfilePhotoUri(fbImage);
                    kid.getPhotosUri().add(new MyPhoto().setPhotoUri(fbImage));

                } else
                    kid.setProfilePhotoUri(Uri.parse("https://firebasestorage.googleapis.com/v0/b/kinderkit-68d4c.appspot.com/o/DEFAULT.jpg?alt=media&token=f55bdee7-a8dd-4e7a-822d-ac0b9b97d873"));
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


    private void uploadImage(Uri image) {
        StorageReference reference = storageReference.child(UUID.randomUUID().toString() + ".jpg");
        reference.putFile(image).addOnSuccessListener(taskSnapshot -> {
            // Image uploaded successfully, now get the download URL
            reference.getDownloadUrl().addOnSuccessListener(uri -> {
                // Get the download URL and use it to store or display the image
                fbImage = uri;
                AK_MB_buttonAddKid.setClickable(true);
                // Do something with the imageUrl, such as storing it in the database
                Toast.makeText(AddKidActivity.this, "Image uploaded successfully", Toast.LENGTH_SHORT).show();
            }).addOnFailureListener(e -> {
                // Handle any errors getting the download URL
                AK_MB_buttonAddKid.setClickable(true);
                Toast.makeText(AddKidActivity.this, "Failed to get download URL", Toast.LENGTH_SHORT).show();
            });
        }).addOnFailureListener(e -> {
            // Handle any errors uploading the image
            AK_MB_buttonAddKid.setClickable(true);
            Toast.makeText(AddKidActivity.this, "Image upload failed", Toast.LENGTH_SHORT).show();
        }).addOnProgressListener(snapshot -> {
            // Observe state change events such as progress, pause, and resume
            // Get the current progress
            AK_MB_buttonAddKid.setClickable(false);
            double progress = (100.0 * snapshot.getBytesTransferred()) / snapshot.getTotalByteCount();
            Toast.makeText(AddKidActivity.this, "Upload In Progress", Toast.LENGTH_SHORT).show();
        });
    }


}