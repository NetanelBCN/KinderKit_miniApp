package dev.netanelbcn.kinderkit.Uilities;

import android.net.Uri;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Comparator;

import dev.netanelbcn.kinderkit.Models.ImmunizationRecord;
import dev.netanelbcn.kinderkit.Models.Kid;
import dev.netanelbcn.kinderkit.Models.KidEvent;

public class DataManager {

    private String uid;
    private ArrayList<Kid> kids;
    private ArrayList<Integer> k_Ids;

//    private Member member;

    private static DataManager instance;
    private FirebaseDatabase firebaseRTDatabase;

    private FBmanager fbmanager;

    public FBmanager getFbmanager() {
        return fbmanager;
    }

    private DataManager() {
        firebaseRTDatabase = FirebaseDatabase.getInstance();
        k_Ids = new ArrayList<>();
        kids = new ArrayList<>();
    }

    public void setUid(FirebaseUser user) {
        this.uid = user.getUid();
        this.fbmanager = new FBmanager(user);
    }

    public void setFbmanager(FirebaseUser user) {

    }

    @Override
    public String toString() {
        return "DataManager{" +
                "kids=" + kids +
                '}';
    }

    public static DataManager getInstance() {
        if (instance == null) {
            instance = new DataManager();
        }
        return instance;
    }

    public String getUid() {
        return uid;
    }

    public DataManager setK_Ids(ArrayList<Integer> k_Ids) {
        this.k_Ids = k_Ids;
        return this;
    }


    public FirebaseDatabase getFirebaseRTDatabase() {
        return firebaseRTDatabase;
    }

    public DataManager setFirebaseRTDatabase(FirebaseDatabase firebaseRTDatabase) {
        this.firebaseRTDatabase = firebaseRTDatabase;
        return this;
    }

    public ArrayList<Integer> getK_Ids() {
        return k_Ids;
    }

    public ArrayList<Kid> getKids() {
        return kids;
    }

    public void addImmunizationRecord(ImmunizationRecord record, Kid kid) {

        kid.getImmunizationRecords().add(record);
        fbmanager.addImmunizationRecordToDB(record, kid);
    }

    public void addKidEvent(KidEvent event, Kid kid) {
        kid.getEvents().add(event);
        fbmanager.addKidEventToDB(event, kid);
    }

    public void removeKid(Kid kid) {
        kids.remove(kid);
        fbmanager.removeKidFromDB(kid.getkId());
    }

    public void removeImmunizationRecord(ImmunizationRecord iR, Kid kid) {
        kid.getImmunizationRecords().remove(iR);
        fbmanager.removeImmunizationRecordFB(iR, kid);
    }

    public void removeKidEvent(KidEvent kEvent, Kid kid) {
        kid.getEvents().remove(kEvent);
        fbmanager.removeKidEventFB(kEvent, kid);
    }
    public DataManager setKids(ArrayList<Kid> kids) {
        this.kids = kids;
        return this;
    }

    public void addKid(Kid kid) {
        kids.add(kid);
        this.kids.sort((o1, o2) -> o2.getAge() - o1.getAge());
        fbmanager.addKidToDB(kid);
    }



    public DataManager InitGeneralData() {
        KidEvent e1 = new KidEvent().setEventTitle("Birthday").setEDate(9, 11, 2020);
        KidEvent e12 = new KidEvent().setEventTitle("TAKE FROM TRIP").setEDate(13, 4, 2021);

        KidEvent e2 = new KidEvent().setEventTitle("Birthday").setEDate(13, 11, 2021);
        KidEvent e3 = new KidEvent().setEventTitle("Birthday").setEDate(28, 12, 2021);
        KidEvent e4 = new KidEvent().setEventTitle("Birthday").setEDate(7, 1, 2021);
        KidEvent e5 = new KidEvent().setEventTitle("Birthday").setEDate(15, 4, 2021);


        ImmunizationRecord i1 = new ImmunizationRecord().setVaccineName("HBV").setDoseNumber(1).setVaccinatorName("Sartori Ofira").setHMOName("Clalit").setvdate(12, 9, 2020).setCreatorName("Phizer");
        ImmunizationRecord i1a = new ImmunizationRecord().setVaccineName("HBV").setDoseNumber(2).setVaccinatorName("Sartori Ofira").setHMOName("Clalit").setvdate(12, 9, 2021).setCreatorName("Phizer");
        ImmunizationRecord i2 = new ImmunizationRecord().setVaccineName("IPV").setDoseNumber(1).setVaccinatorName("Anat Weiner").setHMOName("Macabi").setvdate(8, 12, 2020).setCreatorName("Phizer");
        ImmunizationRecord i3 = new ImmunizationRecord().setVaccineName("DTaP").setDoseNumber(1).setVaccinatorName("Dr. Cohen").setHMOName("Leumit").setvdate(6, 7, 2008).setCreatorName("Phizer");
        ImmunizationRecord i4 = new ImmunizationRecord().setVaccineName("covid19").setDoseNumber(1).setVaccinatorName("Dr. Cohen").setHMOName("Leumit").setvdate(19, 1, 2000).setCreatorName("Phizer");

        Kid Eliya = new Kid().setBirthDate(9, 11, 2018).setfName("Eliya").setlName("Cohen").setProfilePhotoUri(Uri.parse(
                "https://firebasestorage.googleapis.com/v0/b/kinderkit-68d4c.appspot.com/o/eliya.jpg?alt=media&token=9625f48e-5a77-47da-84f1-6130fe6658d5"));
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


        addKid(Eliya);
        addKid(Ariel);
        addKid(Tamar);
        addKid(Daniel);
        addKid(Ilay);
        getKids().sort(Comparator.comparingInt(Kid::getAge).reversed());


        return this;
    }
}
