package dev.netanelbcn.kinderkit.Models;

import android.net.Uri;
import android.util.Log;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Kid {


    private String fName;
    private String lName;
    private MyDate birthDate;
    private int age;
    private ArrayList<Uri> photosUri;
    private Uri profilePhotoUri;
    private ArrayList<ImmunizationRecord> ImmunizationRecords;
    private ArrayList<KidEvent> events;
    private String kId;


    public Kid() {
        this.photosUri = new ArrayList<>();
        this.ImmunizationRecords = new ArrayList<>();
        this.events = new ArrayList<>();
        String id = UUID.randomUUID().toString();
        this.kId = id.toUpperCase();
    }

    public Kid(String kId) {
        this.photosUri = new ArrayList<>();
        this.ImmunizationRecords = new ArrayList<>();
        this.events = new ArrayList<>();
        this.kId = kId;
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

    public Kid setkId(String kId) {
        this.kId = kId;
        return this;
    }

    public String getkId() {
        return this.kId;
    }

    public MyDate getBirthDate() {
        return birthDate;
    }

    public String getlName() {
        return lName;
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

    public Map<String, ImmunizationRecord> getIRMap() {

        Map<String, ImmunizationRecord> map = new HashMap<>();
        for (ImmunizationRecord record : this.getImmunizationRecords()) {
            map.put(record.getIrID(), record);
        }
        return map;
    }

    public Map<String, KidEvent> getKEMap() {

        Map<String, KidEvent> map = new HashMap<>();
        for (KidEvent event : this.getEvents()) {
            map.put(event.geteId(), event);
        }
        return map;
    }

    public ArrayList<ImmunizationRecord> convertIRtoArrayList(Map<String, ImmunizationRecord> map) {
        ArrayList<ImmunizationRecord> iR = new ArrayList<>();
        if (map == null)
            return iR;
        for (Map.Entry<String, ImmunizationRecord> entry : map.entrySet()) {
            Map<String, Object> immunizationData = (Map<String, Object>) entry.getValue();
            Map<String, Object> vdate = (Map<String, Object>) immunizationData.get("vdate");
            MyDate date = new MyDate(((Long) vdate.get("day")).intValue(), ((Long) vdate.get("month")).intValue(), ((Long) vdate.get("year")).intValue());
            ImmunizationRecord record = new ImmunizationRecord().setCreatorName((String) immunizationData.get("creatorName"))
                    .setDoseNumber(((Long) immunizationData.get("doseNumber")).intValue())
                    .setHMOName((String) immunizationData.get("hmoname"))
                    .setVaccinatorName((String) immunizationData.get("vaccinatorName"))
                    .setVaccineName((String) immunizationData.get("vaccineName"))
                    .setvdate(date) // Assuming vdate is stored as a Long
                    .setIrID((String) immunizationData.get("irID"));
            iR.add(record);
        }
        return iR;
    }

    public ArrayList<KidEvent> convertKEtoArrayList(Map<String, KidEvent> map) {
        ArrayList<KidEvent> kE = new ArrayList<>();
        if (map == null)
            return kE;
        for (Map.Entry<String, KidEvent> entry : map.entrySet()) {
            Map<String, Object> eventData = (Map<String, Object>) entry.getValue();
            Map<String, Object> edate = (Map<String, Object>) eventData.get("edate");
            MyDate date = new MyDate(((Long) edate.get("day")).intValue(), ((Long) edate.get("month")).intValue(), ((Long) edate.get("year")).intValue());
            Log.d("05233",eventData.get("eId")+"");
            KidEvent event = new KidEvent().seteId((String) eventData.get("eId"))
                    .setEDate(date)
                    .setEventTitle((String) eventData.get("eventTitle"));
            kE.add(event);
        }
        return kE;
    }

}
