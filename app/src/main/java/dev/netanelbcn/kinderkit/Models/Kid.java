package dev.netanelbcn.kinderkit.Models;

import android.net.Uri;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class Kid {

   public static int AUTO_INCREMENT = 0;
   private String fName;

   private String lName;
   private myDate birthDate;
   private int age;
   private ArrayList <Uri> photosUri;
   private Uri profilePhotoUri;
   private ArrayList <ImmunizationRecord> ImmunizationRecords;
   private ArrayList<KidEvent> events;

   public Kid() {
        this.photosUri = new ArrayList<>();
        this.ImmunizationRecords = new ArrayList<>();
        this.events = new ArrayList<>();
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

   public myDate getBirthDate() {
      return birthDate;
   }

   public String getlName() {
      return lName;
   }

   public Kid setlName(String lName) {
      this.lName = lName;
      return this;
   }

   public Kid setBirthDate(int day,int month,int year) {
      this.birthDate = new myDate(day,year,month);
      this.age = LocalDate.now().getYear() - this.birthDate.getYear();
      if(LocalDate.now().getMonthValue()<this.birthDate.getMonth()){
         this.age--;
      } else if (LocalDate.now().getMonthValue()==this.birthDate.getMonth()) {
         if(LocalDate.now().getDayOfMonth()<this.birthDate.getDay()){
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

   public ArrayList<KidEvent> getEvents() {
      return events;
   }

   public Kid setEvents(ArrayList<KidEvent> events) {
      this.events = events;
      return this;
   }
}
