package dev.netanelbcn.kinderkit.Models;

import java.util.Date;

public class immunizationRecord {
    private String vaccineName;
    private int doseNumber;
    private Date date;
    private String vaccinatorName;
    private String HMOName;
    private String creatorName;

    public immunizationRecord() {
    }

    public immunizationRecord setVaccineName(String vaccineName) {
        this.vaccineName = vaccineName;
        return this;
    }

    public String getVaccineName() {
        return vaccineName;
    }

    public int getDoseNumber() {
        return doseNumber;
    }

    public immunizationRecord setDoseNumber(int doseNumber) {
        this.doseNumber = doseNumber;
        return this;
    }

    public Date getDate() {
        return date;
    }

    public immunizationRecord setDate(Date date) {
        this.date = date;
        return this;
    }

    public String getVaccinatorName() {
        return vaccinatorName;
    }

    public immunizationRecord setVaccinatorName(String vaccinatorName) {
        this.vaccinatorName = vaccinatorName;
        return this;
    }

    public String getHMOName() {
        return HMOName;
    }

    public immunizationRecord setHMOName(String HMOName) {
        this.HMOName = HMOName;
        return this;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public immunizationRecord setCreatorName(String creatorName) {
        this.creatorName = creatorName;
        return this;
    }

    public String toString() {
        return "vaccineName: " + vaccineName + ", doseNumber: " + doseNumber + ", date: " + date + ", vaccinatorName: " + vaccinatorName + ", HMOName: " + HMOName + ", creatorName: " + creatorName;
    }
}
