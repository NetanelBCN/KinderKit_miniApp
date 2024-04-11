package dev.netanelbcn.kinderkit.Uilities;

import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

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

    public void addImmunizationRecord(ImmunizationRecord record, int index) {
        Kid kid = kids.get(index);
        kid.getImmunizationRecords().add(record);
        //   addIMRecordToRTDB(record, kid);
    }

    public void addKidEvent(KidEvent event, int index) {
        Kid kid = kids.get(index);
        kid.getEvents().add(event);
        // addKidEventToRTDB(event, kid.getkId());
    }

    public void removeKid(int index) {
        Kid kid = kids.get(index);
        kids.remove(kid);
        //   removeKidFromRTDB(kid.getkId());
    }

    public void removeImmunizationRecord(int pos, Kid kid) {
        kid.getImmunizationRecords().remove(pos);
        fbmanager.removeImmunizationRecord(pos, kid);
    }

    public void removeKidEvent(KidEvent event, Kid kid) {
        kid.getEvents().remove(event);
        //  removeEventFromRTDB(event, kid.getkId());
    }


    public DataManager setKids(ArrayList<Kid> kids) {
        this.kids = kids;
        return this;
    }

    public void addKid(Kid kid) {
        kids.add(kid);
        if (kid.getkId() == -1)
            kid.setkId(idGenerator());
        fbmanager.addKidToDB(kid);
    }


    public int generateKidId(int index) {
        int newId = idGenerator();
        k_Ids.add(newId);
        return newId;
    }

    private int idGenerator() {
        int num = 3031;
        if (k_Ids.size() == 0) {
            this.k_Ids.add(num);
            return num;
        }
        num = this.k_Ids.get(k_Ids.size() - 1);
        num++;
        while (k_Ids.contains(num)) {
            num++;
        }
        this.k_Ids.add(num);
        return num;
    }














//    public void addImmunizationRecord(ImmunizationRecord record, Kid kid, FirebaseUser user) {
//        DatabaseReference ref = firebaseRTDatabase.getReference("users");
//        ref.child(user.getUid()).child("kids").child(kid.getfName()).child("immunizationRecords").push().setValue(record);
//    }


//    public void LoadData(){
//        fbmanager.getRef().addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                ArrayList<ImmunizationRecord> iR;
//                ArrayList<KidEvent> events;
//                int KidId , day ,month ,year,age;
//                ArrayList<Uri> photosUri;
//                String fname,lname ,profilePhotoUri;
//                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
//                    KidId = snapshot.getchild().getValue(Integer.class);
//                    snapshot.getValue();
//                    YourModelClass model = snapshot.getValue(YourModelClass.class);
//                    // Do something with the model object
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//                // This method will be invoked if the ValueEventListener is cancelled, or if permission denied
//                Log.e("Firebase", "Error: " + databaseError.getMessage());
//            }
//        });


    // Add a ValueEventListener to retrieve data only once


    public DataManager InitGeneralData() {
        KidEvent e1 = new KidEvent().setEventTitle("Birthday").setEDate(9, 11, 2020);
        KidEvent e12 = new KidEvent().setEventTitle("TAKE FROM TRIP").setEDate(13, 4, 2021);

        KidEvent e2 = new KidEvent().setEventTitle("Birthday").setEDate(13, 11, 2021);
        KidEvent e3 = new KidEvent().setEventTitle("Birthday").setEDate(28, 12, 2021);
        KidEvent e4 = new KidEvent().setEventTitle("Birthday").setEDate(7, 1, 2021);
        KidEvent e5 = new KidEvent().setEventTitle("Birthday").setEDate(15, 4, 2021);


        ImmunizationRecord i1 = new ImmunizationRecord().setVaccineName("HBV").setDoseNumber(1).setVaccinatorName("Sartori Ofira").setHMOName("Clalit").setvdate(12, 9, 2020);
        ImmunizationRecord i1a = new ImmunizationRecord().setVaccineName("HBV").setDoseNumber(2).setVaccinatorName("Sartori Ofira").setHMOName("Clalit").setvdate(12, 9, 2021);
        ImmunizationRecord i2 = new ImmunizationRecord().setVaccineName("IPV").setDoseNumber(1).setVaccinatorName("Anat Weiner").setHMOName("Macabi").setvdate(8, 12, 2020);
        ImmunizationRecord i3 = new ImmunizationRecord().setVaccineName("DTaP").setDoseNumber(1).setVaccinatorName("Dr. Cohen").setHMOName("Leumit").setvdate(6, 7, 2008);
        ImmunizationRecord i4 = new ImmunizationRecord().setVaccineName("covid19").setDoseNumber(1).setVaccinatorName("Dr. Cohen").setHMOName("Leumit").setvdate(19, 1, 2000);

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
