package dev.netanelbcn.kinderkit.Views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

import dev.netanelbcn.kinderkit.Adapters.EvenetAdapter;
import dev.netanelbcn.kinderkit.Models.KidEvent;
import dev.netanelbcn.kinderkit.R;
import dev.netanelbcn.kinderkit.Uilities.DataManager;

public class EventKidActivity extends AppCompatActivity {
    private EvenetAdapter adapter;
    private ArrayList<KidEvent> events;
    private int currentKidPosition;
    private RecyclerView EA_RV_events;
    private MaterialButton EA_MB_add_event;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_event_kid);
        connectUI();
        getIntents();
        attachListeners();
        events = DataManager.getInstance().getKids().get(currentKidPosition).getEvents();
        EA_RV_events.setLayoutManager(new
                LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        adapter = new EvenetAdapter(this, events);
        adapter.setEventCallback((event, position) -> {
            events.remove(position);
            adapter.notifyDataSetChanged();
        });
        EA_RV_events.setAdapter(adapter);

    }

    private void attachListeners() {
        EA_MB_add_event.setOnClickListener(v -> {
            Intent intent = new Intent(EventKidActivity.this, AddEventActivity.class);
            intent.putExtra("kidPosition", currentKidPosition);
            startActivity(intent);
        });
    }

    private void getIntents() {
        currentKidPosition = getIntent().getIntExtra("kidPosition", -1);
    }

    private void connectUI() {
        EA_RV_events = findViewById(R.id.EA_RV_events);
        EA_MB_add_event = findViewById(R.id.EA_MB_add_event);
    }

    public void refreshEventsList() {
        events.sort((o1, o2) -> o1.getEDate().compareTo(o2.getEDate()));
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshEventsList();
    }
}