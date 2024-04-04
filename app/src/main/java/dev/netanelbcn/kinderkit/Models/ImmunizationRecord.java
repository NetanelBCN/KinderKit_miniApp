package dev.netanelbcn.kinderkit.Models;

import androidx.annotation.NonNull;

import java.util.Date;

public class ImmunizationRecord {
    private String vaccineName;
    private int doseNumber;
    private myDate vDate;

    private String vaccinatorName;
    private String HMOName;
    private String creatorName;

    public ImmunizationRecord() {
    }

    public ImmunizationRecord setVaccineName(String vaccineName) {
        this.vaccineName = vaccineName;
        return this;
    }

    public String getVaccineName() {
        return vaccineName;
    }

    public int getDoseNumber() {
        return doseNumber;
    }

    public ImmunizationRecord setDoseNumber(int doseNumber) {
        this.doseNumber = doseNumber;
        return this;
    }


    public myDate getvDate() {
        return vDate;
    }



    public ImmunizationRecord setVDate(int day,int month,int year) {
       this.vDate= new myDate(day,year,month);
        return this;
    }

    public String getVaccinatorName() {
        return vaccinatorName;
    }

    public ImmunizationRecord setVaccinatorName(String vaccinatorName) {
        this.vaccinatorName = vaccinatorName;
        return this;
    }

    public String getHMOName() {
        return HMOName;
    }

    public ImmunizationRecord setHMOName(String HMOName) {
        this.HMOName = HMOName;
        return this;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public ImmunizationRecord setCreatorName(String creatorName) {
        this.creatorName = creatorName;
        return this;
    }

    @Override
    public String toString() {
        return "ImmunizationRecord{" +
                "vaccineName='" + vaccineName + '\'' +
                ", doseNumber=" + doseNumber +
                ", vDate=" + vDate.toString() +
                ", vaccinatorName='" + vaccinatorName + '\'' +
                ", HMOName='" + HMOName + '\'' +
                ", creatorName='" + creatorName + '\'' +
                '}';
    }
}
