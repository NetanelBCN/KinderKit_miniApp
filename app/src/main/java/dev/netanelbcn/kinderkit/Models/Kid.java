package dev.netanelbcn.kinderkit.Models;

import android.net.Uri;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Kid {


    private String fName;
    private String lName;
    private MyDate birthDate;
    private int age;
    private ArrayList<Uri> photosUri;
    private Uri profilePhotoUri;
    private ArrayList<ImmunizationRecord> ImmunizationRecords;
    private ArrayList<KidEvent> events;
    private int kId;




    public Kid() {
        this.photosUri = new ArrayList<>();
        this.ImmunizationRecords = new ArrayList<>();
        this.events = new ArrayList<>();
        this.kId=-1;
    }

    public int getAge() {
        return age;
    }


    public String getfName() {
        return fName;
    }

    public Kid setfName(String fName) {
        this.fName = fName;
        return this;
    }
    public Kid setkId(int kId) {
        this.kId = kId;
        return this;
    }

    public int getkId() {
        return kId;
    }

    public MyDate getBirthDate() {
        return birthDate;
    }

    public String getlName() {
        return lName;
    }

    public Kid setBirthDate(MyDate birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    public Kid setAge(int age) {
        this.age = age;
        return this;
    }

    public Kid setlName(String lName) {
        this.lName = lName;
        return this;
    }

    public Kid setBirthDate(int day, int month, int year) {
        this.birthDate = new MyDate(day, month, year);
        this.age = LocalDate.now().getYear() - this.birthDate.getYear();
        if (LocalDate.now().getMonthValue() < this.birthDate.getMonth()) {
            this.age--;
        } else if (LocalDate.now().getMonthValue() == this.birthDate.getMonth()) {
            if (LocalDate.now().getDayOfMonth() < this.birthDate.getDay()) {
                this.age--;
            }
        }
        return this;
    }

    public ArrayList<Uri> getPhotosUri() {
        return photosUri;
    }

    public Kid setPhotosUri(ArrayList<Uri> photosUri) {
        this.photosUri = photosUri;
        return this;
    }

    public Uri getProfilePhotoUri() {
        return profilePhotoUri;
    }

    public Kid setProfilePhotoUri(Uri profilePhotoUri) {
        this.profilePhotoUri = profilePhotoUri;
        return this;
    }

    public ArrayList<ImmunizationRecord> getImmunizationRecords() {
        return ImmunizationRecords;
    }

    public Kid setImmunizationRecords(ArrayList<ImmunizationRecord> ImmunizationRecords) {
        this.ImmunizationRecords = ImmunizationRecords;
        return this;
    }

    public int getDoseNumber(ArrayList<ImmunizationRecord> records, String vaccineName) {
        int doseNumber = 1;
        for (ImmunizationRecord record : records) {
            if (record.getVaccineName().equalsIgnoreCase(vaccineName)) {
                doseNumber = record.getDoseNumber() + 1;
            }
        }
        return doseNumber;

    }

    public ArrayList<KidEvent> getEvents() {
        return events;
    }

    public Kid setEvents(ArrayList<KidEvent> events) {
        this.events = events;
        return this;
    }

    @Override
    public String toString() {
        return "Kid{" +
                "fName='" + fName + '\'' +
                ", lName='" + lName + '\'' +
                ", birthDate=" + birthDate +
                ", age=" + age +
                ", photosUri=" + photosUri +
                ", profilePhotoUri=" + profilePhotoUri +
                ", ImmunizationRecords=" + ImmunizationRecords +
                ", events=" + events +
                ", kId=" + kId +
                '}';
    }

    public Map<String,ImmunizationRecord> getIRMap(){

        Map<String,ImmunizationRecord> map = new HashMap<>();
        for (ImmunizationRecord record : this.getImmunizationRecords()) {
            map.put(record.getIrID(),record);
        }
        return map;
    }

//    public Map<String, Object> toMap() {
//        Map<String, Object> map = new HashMap<>();
//        map.put("fName", fName);
//        map.put("lName", lName);
//        map.put("birthDate", birthDate);
//        map.put("age", age);
//        map.put("photosUri", photosUri);
//        map.put("profilePhotoUri", profilePhotoUri);
//        map.put("kId", kId);
//        // Convert ArrayLists to ArrayLists of HashMaps
//        ArrayList<Map<String, Object>> immunizationRecordsMap = new ArrayList<>();
//        for (ImmunizationRecord record : this.getImmunizationRecords()) {
//            Map<String, Object> recordMap = new HashMap<>();
//            recordMap.put("vaccineName", record.getVaccineName());
//            recordMap.put("doseNumber", record.getDoseNumber());
//            recordMap.put("vaccinatorName", record.getVaccinatorName());
//            recordMap.put("hmoName", record.getHMOName());
//            recordMap.put("vDate", record.getvdate());
//            immunizationRecordsMap.add(recordMap);
//        }
//        map.put("immunizationRecords", immunizationRecordsMap);
//        ArrayList<Map<String, Object>> eventsMap = new ArrayList<>();
//        for (KidEvent event : events) {
//            Map<String, Object> eventMap = new HashMap<>();
//            eventMap.put("eventTitle", event.getEventTitle());
//            eventMap.put("eDate", event.getEDate());
//            eventsMap.add(eventMap);
//        }
//        map.put("events", eventsMap);
//        return map;
//    }
}
