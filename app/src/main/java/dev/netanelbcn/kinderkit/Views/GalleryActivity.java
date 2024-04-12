package dev.netanelbcn.kinderkit.Views;

import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

import dev.netanelbcn.kinderkit.Adapters.GalleryAdapter;
import dev.netanelbcn.kinderkit.Models.Kid;
import dev.netanelbcn.kinderkit.R;
import dev.netanelbcn.kinderkit.Uilities.DataManager;

public class GalleryActivity extends AppCompatActivity {

    public GalleryAdapter adapter;
    private ArrayList<Uri> images;
    private int currentKidPosition;

    private RecyclerView GA_RV_gallery;
    private MaterialButton EA_MB_add_event;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_gallery);
        connectUI();
        getIntents();
        attachListeners();
        Kid myKid = DataManager.getInstance().getKids().get(currentKidPosition);
        images = myKid.getPhotosUri();
        GA_RV_gallery.setLayoutManager(new
                LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        adapter = new GalleryAdapter(this, images);
//        adapter.setDelPicCallback((uri) -> {
//            DataManager.getInstance().removePhotoUri(uri, myKid);
//            adapter.notifyDataSetChanged();
//        });
//        adapter.setSetAsProfilePictureCallback((uri) -> {
//            DataManager.getInstance().setProfilePhotoUri(uri, myKid);
//            adapter.notifyDataSetChanged();
//            Uri newProfilePictureUri = myKid.getProfilePhotoUri();
//            // Update your UI with the new profile picture URI
//        });
        adapter.setSetAsProfilePictureCallback((uri) -> {
            DataManager.getInstance().setProfilePhotoUri(uri, myKid);
            adapter.notifyDataSetChanged();
            finish();
        });
        GA_RV_gallery.setAdapter(adapter);

    }

    private void attachListeners() {
        EA_MB_add_event.setOnClickListener(v -> {
            //add picture methods
        });
    }

    private void getIntents() {
        currentKidPosition = getIntent().getIntExtra("kidPosition", -1);
    }


    private void connectUI() {
        GA_RV_gallery = findViewById(R.id.GA_RV_gallery);
        EA_MB_add_event = findViewById(R.id.EA_MB_add_event);
    }
}