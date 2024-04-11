package dev.netanelbcn.kinderkit.Views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

import dev.netanelbcn.kinderkit.Adapters.IRAdapter;
import dev.netanelbcn.kinderkit.Models.ImmunizationRecord;
import dev.netanelbcn.kinderkit.Models.Kid;
import dev.netanelbcn.kinderkit.R;
import dev.netanelbcn.kinderkit.Uilities.DataManager;

public class ImmunizationsActivity extends AppCompatActivity {

    private IRAdapter adapter;
    private ArrayList<ImmunizationRecord> records;
    private int currentKidPosition;
    private RecyclerView IA_RV_immunizationRecord;
    private MaterialButton EA_MB_add_record;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_immunizations);
        connectUI();
        getIntents();
        attachListeners();
        Kid myKid= DataManager.getInstance().getKids().get(currentKidPosition);
        records = myKid.getImmunizationRecords();
        IA_RV_immunizationRecord.setLayoutManager(new
                LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        adapter = new IRAdapter(this, records);
        adapter.setIRCallback((record, position) -> {
           DataManager.getInstance().removeImmunizationRecord(record,myKid);
            adapter.notifyDataSetChanged();
        });
        IA_RV_immunizationRecord.setAdapter(adapter);
    }

    private void attachListeners() {
        EA_MB_add_record.setOnClickListener(v -> {

            Intent intent = new Intent(ImmunizationsActivity.this, AddRecordActivity.class);
            intent.putExtra("kidPosition", currentKidPosition);
            startActivity(intent);

        });

    }

    private void getIntents() {
        currentKidPosition = getIntent().getIntExtra("kidPosition", -1);
    }

    private void connectUI() {
        IA_RV_immunizationRecord = findViewById(R.id.IA_RV_immunizationRecord);
        EA_MB_add_record = findViewById(R.id.EA_MB_add_record);
    }

    private void refreshRecordsList() {
        records.sort((o1, o2) -> o1.getvdate().compareTo(o2.getvdate()));
        adapter.notifyDataSetChanged();
    }


    @Override
    protected void onResume() {
        super.onResume();
        refreshRecordsList();
    }


}