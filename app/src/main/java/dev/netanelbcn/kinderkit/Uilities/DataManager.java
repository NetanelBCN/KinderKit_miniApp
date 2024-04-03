package dev.netanelbcn.kinderkit.Uilities;

import android.content.Context;
import android.net.Uri;

import java.util.ArrayList;

import dev.netanelbcn.kinderkit.Models.Kid;

public class DataManager {

//    private static volatile DataManager instance = null;
    private ArrayList<Kid> kids;

    public DataManager() {
    }

    public DataManager InitGeneralData(){
        kids = new ArrayList<>();
        kids.add(new Kid().setfName("Eliya").setlName("Cohen").setProfilePhotoUri(Uri.parse("https://firebasestorage.googleapis.com/v0/b/kinderkit-68d4c.appspot.com/o/eliya.jpg?alt=media&token=9625f48e-5a77-47da-84f1-6130fe6658d5")));
        kids.add(new Kid().setfName("daniel").setlName("Niazov").setProfilePhotoUri(Uri.parse("https://firebasestorage.googleapis.com/v0/b/kinderkit-68d4c.appspot.com/o/Daniel.jpg?alt=media&token=9715d152-ca07-4822-ad0d-8e1efcd759e1")));
        kids.add(new Kid().setfName("tamar").setlName("Niazov").setProfilePhotoUri(Uri.parse("https://firebasestorage.googleapis.com/v0/b/kinderkit-68d4c.appspot.com/o/Tamar.jpg?alt=media&token=4d636048-1601-44a0-9250-71a1a0df94ef")));
        kids.add(new Kid().setfName("Ilay").setlName("Cohen").setProfilePhotoUri(Uri.parse("https://firebasestorage.googleapis.com/v0/b/kinderkit-68d4c.appspot.com/o/Ilay.jpg?alt=media&token=3f7ea009-c34d-4692-b015-9b08fc5468a3")));
        kids.add(new Kid().setfName("Ariel").setlName("Niazov").setProfilePhotoUri(Uri.parse("https://firebasestorage.googleapis.com/v0/b/kinderkit-68d4c.appspot.com/o/Ariel.jpg?alt=media&token=3a1bc07a-b643-4a44-9769-b62b2eb7001b")));
    return this;
    }

    public ArrayList<Kid> getKids() {
        return kids;
    }

    public DataManager setKids(ArrayList<Kid> kids) {
        this.kids = kids;
        return this;
    }
//    public static void init() {
//        synchronized (DataManager.class) {
//            if (instance == null) {
//                instance = new DataManager();
//            }
//        }
//    }
//
//    public static DataManager getInstance() {
//        return instance;
//    }
}
