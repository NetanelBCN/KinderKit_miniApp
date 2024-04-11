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

import dev.netanelbcn.kinderkit.Interfaces.DataLoadCallback;
import dev.netanelbcn.kinderkit.Models.ImmunizationRecord;
import dev.netanelbcn.kinderkit.Models.Kid;
import dev.netanelbcn.kinderkit.Models.KidEvent;
import dev.netanelbcn.kinderkit.Models.MyDate;


public class FBmanager {
    private DatabaseReference ref;
    private FirebaseDatabase firebaseRTDatabase;
    private FirebaseUser user;

    private ArrayList<Kid> kids;

    public FBmanager(FirebaseUser user) {
        this.firebaseRTDatabase = FirebaseDatabase.getInstance();
        this.user = user;
        this.kids = new ArrayList<>();
        ref = firebaseRTDatabase.getReference(this.user.getUid());
    }

    public ArrayList<Kid> getKids() {
        return kids;
    }

    public DatabaseReference getRef() {
        return ref;
    }

    public void LoadDataFromFBRTDB(DataLoadCallback callback) {

        DatabaseReference myRef = firebaseRTDatabase.getReference(this.user.getUid());
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot kidSnapshot : dataSnapshot.getChildren()) {
                    int kidId = Integer.parseInt(kidSnapshot.getKey());
                    Log.d("KidId", kidId + "");
                    Kid myKid = buildKidBasicInfo(kidSnapshot);
                    DataSnapshot immunizationsSnapshot = kidSnapshot.child("Immunizations");
                    DataSnapshot eventsSnapshot = kidSnapshot.child("Events");
                    myKid.setEvents(buildEventRecords(eventsSnapshot));
                    myKid.setImmunizationRecords(buildImmunizationRecords(immunizationsSnapshot));
                    kids.add(myKid);
                }
                callback.onDataLoaded();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle errors
                Log.w("RecipeData", "Failed to read value.", databaseError.toException());
            }
        });
    }

    private ArrayList<KidEvent> buildEventRecords(DataSnapshot eventSnapshot) {
        ArrayList<KidEvent> events = new ArrayList<>();
        for (DataSnapshot snapshot : eventSnapshot.getChildren()) {
            String eventTitle = snapshot.child("eventTitle").getValue(String.class);
            int day = snapshot.child("edate").child("day").getValue(Integer.class);
            int month = snapshot.child("edate").child("month").getValue(Integer.class);
            int year = snapshot.child("edate").child("year").getValue(Integer.class);
            KidEvent event = new KidEvent().setEventTitle(eventTitle).setEDate(day, month, year);
            events.add(event);
        }
        return events;
    }

    private Kid buildKidBasicInfo(DataSnapshot kidSnapshot) {
        String fName = kidSnapshot.child("name").child("fName").getValue(String.class);
        String lName = kidSnapshot.child("name").child("lName").getValue(String.class);
        int day = kidSnapshot.child("birthDate").child("day").getValue(Integer.class);
        int month = kidSnapshot.child("birthDate").child("month").getValue(Integer.class);
        int year = kidSnapshot.child("birthDate").child("year").getValue(Integer.class);
        int age = kidSnapshot.child("age").getValue(Integer.class);
        String profilePhotoUri = kidSnapshot.child("profilePhotoUri").getValue(String.class);
        return new Kid().setAge(age).setBirthDate(day, month, year).setfName(fName).setlName(lName).setProfilePhotoUri(Uri.parse(profilePhotoUri));
    }

    public ArrayList<ImmunizationRecord> buildImmunizationRecords(DataSnapshot immunizationsSnapshot) {
        ArrayList<ImmunizationRecord> iR = new ArrayList<>();
        for (DataSnapshot immunizationSnapshot : immunizationsSnapshot.getChildren()) {
            String vaccineName = immunizationSnapshot.child("vaccineName").getValue(String.class);
            int doseNumber = immunizationSnapshot.child("doseNumber").getValue(Integer.class);
            String vaccinatorName = immunizationSnapshot.child("vaccinatorName").getValue(String.class);
            String HMOName = immunizationSnapshot.child("HMOName").getValue(String.class);
            String creatorName = immunizationSnapshot.child("creatorName").getValue(String.class);
            int vDay = immunizationSnapshot.child("vdate").child("day").getValue(Integer.class);
            int vMonth = immunizationSnapshot.child("vdate").child("month").getValue(Integer.class);
            int vYear = immunizationSnapshot.child("vdate").child("year").getValue(Integer.class);
            MyDate vdate = new MyDate(vDay, vMonth, vYear);
            ImmunizationRecord record = new ImmunizationRecord(vaccineName, doseNumber, vdate, vaccinatorName, HMOName, creatorName);
            iR.add(record);
        }
        return iR;
    }

    public void addKidToDB(Kid kid) {
        addKidBaseInfoToDB(kid);
        addKidIRToDB(kid, kid.getImmunizationRecords());
        addKidEventToDB(kid, kid.getEvents());
    }

    private void addKidIRToDB(Kid kid, ArrayList<ImmunizationRecord> records) {
        ref.child(kid.getkId() + "").child("Immunizations").setValue(kid.getIRMap());
        int x=0;

    }

    private void addKidEventToDB(Kid kid, ArrayList<KidEvent> events) {
        ref.child(kid.getkId() + "").child("Events").setValue(kid.getEvents());

    }

    private void addKidBaseInfoToDB(Kid kid) {
        ref.child(kid.getkId() + "").child("name").child("fName").setValue(kid.getfName());
        ref.child(kid.getkId() + "").child("name").child("lName").setValue(kid.getlName());
        ref.child(kid.getkId() + "").child("birthDate").setValue(kid.getBirthDate());
        ref.child(kid.getkId() + "").child("age").setValue(kid.getAge());
        ref.child(kid.getkId() + "").child("photosUri").setValue(kid.getPhotosUri());
        ref.child(kid.getkId() + "").child("profilePhotoUri").setValue(kid.getProfilePhotoUri().toString());
    }


    public void removeImmunizationRecord(int pos, Kid kid) {

        ref.child(kid.getkId() + "").child("Immunizations").child(kid.getImmunizationRecords().get(pos).getIrID()).removeValue();
    }
}