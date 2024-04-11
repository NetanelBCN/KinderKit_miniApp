package dev.netanelbcn.kinderkit.Views;

import android.os.Bundle;
import android.widget.DatePicker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.AppCompatEditText;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

import dev.netanelbcn.kinderkit.Models.KidEvent;
import dev.netanelbcn.kinderkit.R;
import dev.netanelbcn.kinderkit.Uilities.DataManager;

public class AddEventActivity extends AppCompatActivity {
    private AppCompatEditText AE_ET_eventName;
    private DatePicker AE_MDP_eventDate;
    private MaterialButton AR_MB_add_record;
    private ArrayList<KidEvent> kidEvents;
    private int currentKidPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_add_event);
        connectUI();
        getIntents();
        attachListeners();

    }

    private void attachListeners() {
        AR_MB_add_record.setOnClickListener(v -> {
            String eventName;
            if (!AE_ET_eventName.getText().toString().isEmpty())
                eventName = AE_ET_eventName.getText().toString();
            else
                eventName = "Untitled Event";
            int day = AE_MDP_eventDate.getDayOfMonth();
            int month = AE_MDP_eventDate.getMonth();
            int year = AE_MDP_eventDate.getYear();
            KidEvent kidEvent = new KidEvent().setEventTitle(eventName).setEDate(day, month+1, year);
            for (KidEvent event : kidEvents) {
                if (event.getEventTitle().equals(kidEvent.getEventTitle()) && event.getEDate().equals(kidEvent.getEDate())) {
                    finish();
                    return;
                }
            }
            kidEvents.add(kidEvent);
            finish();
        });
    }

    private void getIntents() {
        currentKidPosition = getIntent().getIntExtra("kidPosition", -1);
        kidEvents = DataManager.getInstance().getKids().get(currentKidPosition).getEvents();
    }

    private void connectUI() {
        AE_ET_eventName = findViewById(R.id.AE_ET_eventName);
        AE_MDP_eventDate = findViewById(R.id.AE_MDP_eventDate);
        AR_MB_add_record = findViewById(R.id.AR_MB_add_record);
    }
}