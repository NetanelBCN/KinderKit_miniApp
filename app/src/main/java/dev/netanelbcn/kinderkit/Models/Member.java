//package dev.netanelbcn.kinderkit.Models;
//
//import java.util.ArrayList;
//
//public class Member {
//    private String uid;
//    private ArrayList<Kid> kids;
//    private ArrayList<Integer> k_Ids;
//
//    public Member(String uid) {
//        this.uid = uid;
//        k_Ids = new ArrayList<>();
//        kids = new ArrayList<>();
//    }
//
//    public void addKid(Kid kid) {
//        if (kid.getkId() == -1)
//            kid.setkId(idGenerator());
//        kids.add(kid);
//        // addKidToRTDB(kid);
//    }
//
//
//
//
//
//
//    public int generateKidId(int index) {
//        int newId = idGenerator();
//        k_Ids.add(newId);
//        return newId;
//    }
//
//    private int idGenerator() {
//        int num = 3031;
//        if (k_Ids.size() == 0) {
//            return num;
//        }
//        num = this.k_Ids.get(k_Ids.size() - 1);
//        num++;
//        while (k_Ids.contains(num)) {
//            num++;
//        }
//        return num;
//    }
//
//
//}
