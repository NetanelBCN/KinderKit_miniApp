package dev.netanelbcn.kinderkit.Uilities;

import android.content.Context;
import android.icu.text.LocaleDisplayNames;
import android.net.Uri;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;

import dev.netanelbcn.kinderkit.Models.ImmunizationRecord;
import dev.netanelbcn.kinderkit.Models.Kid;
import dev.netanelbcn.kinderkit.Models.KidEvent;

public class DataManager {

    //    private static volatile DataManager instance = null;
    private ArrayList<Kid> kids;
    private ArrayList<ImmunizationRecord> records;
    private static DataManager instance;


    private DataManager() {
    }


    public static DataManager getInstance() {
        if (instance == null) {
            instance = new DataManager();
        }
        return instance;
    }

    public DataManager InitGeneralData() {
        kids = new ArrayList<>();
        records = new ArrayList<>();


        KidEvent e1 = new KidEvent().setEventTitle("Birthday").setEDate(9, 11, 2020);
        KidEvent e12 = new KidEvent().setEventTitle("TAKE FROM TRIP").setEDate(13, 4, 2021);

        KidEvent e2 = new KidEvent().setEventTitle("Birthday").setEDate(13, 11, 2021);
        KidEvent e3 = new KidEvent().setEventTitle("Birthday").setEDate(28, 12, 2021);
        KidEvent e4 = new KidEvent().setEventTitle("Birthday").setEDate(7, 1, 2021);
        KidEvent e5 = new KidEvent().setEventTitle("Birthday").setEDate(15, 4, 2021);


        ImmunizationRecord i1 = new ImmunizationRecord().setVaccineName("HBV").setDoseNumber(1).setVaccinatorName("Sartori Ofira").setHMOName("Clalit").setVDate(12, 9, 2020);
        ImmunizationRecord i1a = new ImmunizationRecord().setVaccineName("HBV").setDoseNumber(2).setVaccinatorName("Sartori Ofira").setHMOName("Clalit").setVDate(12, 9, 2021);
        ImmunizationRecord i2 = new ImmunizationRecord().setVaccineName("IPV").setDoseNumber(1).setVaccinatorName("Anat Weiner").setHMOName("Macabi").setVDate(8, 12, 2020);
        ImmunizationRecord i3 = new ImmunizationRecord().setVaccineName("DTaP").setDoseNumber(1).setVaccinatorName("Dr. Cohen").setHMOName("Leumit").setVDate(6, 7, 2008);
        ImmunizationRecord i4 = new ImmunizationRecord().setVaccineName("covid19").setDoseNumber(1).setVaccinatorName("Dr. Cohen").setHMOName("Leumit").setVDate(19, 1, 2000);

        Kid Eliya = new Kid().setBirthDate(9, 11, 2018).setfName("Eliya").setlName("Cohen").setProfilePhotoUri(Uri.parse("https://firebasestorage.googleapis.com/v0/b/kinderkit-68d4c.appspot.com/o/eliya.jpg?alt=media&token=9625f48e-5a77-47da-84f1-6130fe6658d5"));
        Kid Ariel = new Kid().setBirthDate(13, 11, 2022).setfName("Ariel").setlName("Niazov").setProfilePhotoUri(Uri.parse("https://firebasestorage.googleapis.com/v0/b/kinderkit-68d4c.appspot.com/o/Ariel.jpg?alt=media&token=3a1bc07a-b643-4a44-9769-b62b2eb7001b"));
        Kid Tamar = new Kid().setBirthDate(28, 12, 2020).setfName("tamar").setlName("Niazov").setProfilePhotoUri(Uri.parse("https://firebasestorage.googleapis.com/v0/b/kinderkit-68d4c.appspot.com/o/Tamar.jpg?alt=media&token=4d636048-1601-44a0-9250-71a1a0df94ef"));
        Kid Daniel = new Kid().setBirthDate(7, 1, 2020).setfName("daniel").setlName("Niazov").setProfilePhotoUri(Uri.parse("https://firebasestorage.googleapis.com/v0/b/kinderkit-68d4c.appspot.com/o/Daniel.jpg?alt=media&token=9715d152-ca07-4822-ad0d-8e1efcd759e1"));
        Kid Ilay = new Kid().setBirthDate(15, 4, 2021).setfName("Ilay").setlName("Cohen").setProfilePhotoUri(Uri.parse("https://firebasestorage.googleapis.com/v0/b/kinderkit-68d4c.appspot.com/o/Ilay.jpg?alt=media&token=3f7ea009-c34d-4692-b015-9b08fc5468a3"));
        Eliya.getImmunizationRecords().add(i1);
        Eliya.getImmunizationRecords().add(i1);
        Eliya.getImmunizationRecords().add(i1);
        Eliya.getImmunizationRecords().add(i1a);
        Ilay.getImmunizationRecords().add(i2);
        Ilay.getImmunizationRecords().add(i3);
        Ilay.getImmunizationRecords().add(i4);

        Eliya.getEvents().add(e1);
        Eliya.getEvents().add(e12);
        Eliya.getEvents().add(e12);
        Eliya.getEvents().add(e12);
        Eliya.getEvents().add(e12);
        Eliya.getEvents().add(e12);
        Eliya.getEvents().add(e12);
        Eliya.getEvents().add(e12);
        Eliya.getEvents().add(e12);
        Eliya.getEvents().add(e12);
        Ariel.getEvents().add(e2);
        Tamar.getEvents().add(e3);
        Daniel.getEvents().add(e4);
        Ilay.getEvents().add(e5);


        kids.add(Eliya);
        kids.add(Ariel);
        kids.add(Tamar);
        kids.add(Daniel);
        kids.add(Ilay);
        kids.sort(Comparator.comparingInt(Kid::getAge).reversed());
        return this;
    }

    public ArrayList<Kid> getKids() {
        return kids;
    }

    public ArrayList<ImmunizationRecord> getRecords() {
        return records;
    }

    public DataManager setKids(ArrayList<Kid> kids) {
        this.kids = kids;
        return this;
    }

    public int getDoseNumber(ArrayList<ImmunizationRecord> records, String vaccineName) {
        int doseNumber = 1;
        for (ImmunizationRecord record : records) {
            if (record.getVaccineName().equals(vaccineName)) {
                doseNumber = record.getDoseNumber() + 1;
            }
        }
        return doseNumber;

    }
}
