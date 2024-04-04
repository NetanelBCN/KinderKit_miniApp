package dev.netanelbcn.kinderkit.Views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.AppCompatEditText;

import android.os.Bundle;
import android.widget.DatePicker;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

import dev.netanelbcn.kinderkit.Models.ImmunizationRecord;
import dev.netanelbcn.kinderkit.R;
import dev.netanelbcn.kinderkit.Uilities.DataManager;

public class AddRecordActivity extends AppCompatActivity {

    private AppCompatEditText AR_ET_vaccineName;
    private AppCompatEditText AR_ET_vaccinatorName;
    private AppCompatEditText AR_ET_HMOName;
    private AppCompatEditText AR_ET_creatorName;
    private DatePicker datePicker;
    private MaterialButton AR_MB_add_record;
    private ArrayList<ImmunizationRecord> records;
    private int currentKidPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_add_record);
        connectUI();
        getIntents();
        attachListeners();
    }

    private void getIntents() {
        currentKidPosition = getIntent().getIntExtra("kidPosition", -1);
        records = DataManager.getInstance().getKids().get(currentKidPosition).getImmunizationRecords();
    }

    private void attachListeners() {
        AR_MB_add_record.setOnClickListener(v -> {
            String vaccineName = AR_ET_vaccineName.getText().toString();
            String vaccinatorName = AR_ET_vaccinatorName.getText() != null ? AR_ET_vaccinatorName.getText().toString() : "";
            String HMOName = AR_ET_HMOName.getText() != null ? AR_ET_HMOName.getText().toString() : "";
            String creatorName = AR_ET_creatorName.getText() != null ? AR_ET_creatorName.getText().toString() : "";
            if (!vaccineName.isEmpty()) {
                int day = datePicker.getDayOfMonth();
                int month = datePicker.getMonth();
                int year = datePicker.getYear();
                int doseNum = DataManager.getInstance().getDoseNumber(records, vaccineName);
                ImmunizationRecord iR = new ImmunizationRecord().setDoseNumber(doseNum).setVaccineName(vaccineName).setVaccinatorName(vaccinatorName).setHMOName(HMOName).setCreatorName(creatorName).setVDate(day, month, year);
                records.add(iR);
            }
            finish();
        });
    }

    private void connectUI() {
        AR_ET_vaccineName = findViewById(R.id.AR_ET_vaccineName);
        AR_ET_vaccinatorName = findViewById(R.id.AR_ET_vaccinatorName);
        AR_ET_HMOName = findViewById(R.id.AR_ET_HMOName);
        AR_ET_creatorName = findViewById(R.id.AR_ET_creatorName);
        datePicker = findViewById(R.id.datePicker);
        AR_MB_add_record = findViewById(R.id.AR_MB_add_record);
    }
}