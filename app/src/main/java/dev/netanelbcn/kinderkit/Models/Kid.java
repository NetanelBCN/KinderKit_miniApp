package dev.netanelbcn.kinderkit.Models;

import android.net.Uri;

import java.util.ArrayList;
import java.util.Date;

public class Kid {

   private String fName;
   private String lName;
   private Date birthDate;
   private ArrayList <Uri> photosUri;
   private Uri profilePhotoUri;
   private ArrayList <immunizationRecord> immunizationRecords;
   private ArrayList<KidEvent> events;

   public String getfName() {
      return fName;
   }

   public Kid setfName(String fName) {
      this.fName = fName;
      return this;
   }

   public String getlName() {
      return lName;
   }

   public Kid setlName(String lName) {
      this.lName = lName;
      return this;
   }

   public Date getBirthDate() {
      return birthDate;
   }

   public Kid setBirthDate(Date birthDate) {
      this.birthDate = birthDate;
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

   public ArrayList<immunizationRecord> getImmunizationRecords() {
      return immunizationRecords;
   }

   public Kid setImmunizationRecords(ArrayList<immunizationRecord> immunizationRecords) {
      this.immunizationRecords = immunizationRecords;
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
