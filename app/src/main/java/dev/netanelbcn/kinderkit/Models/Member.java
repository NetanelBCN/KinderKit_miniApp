package dev.netanelbcn.kinderkit.Models;

public class Member {
    private String uid;
    private String email;
    private String phone;
    private String name;
    private String photoUri;

    public Member() {
    }

    public Member setUid(String uid) {
        this.uid = uid;
        return this;
    }

    public Member setEmail(String email) {
        this.email = email;
        return this;
    }

    public Member setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public Member setName(String name) {
        this.name = name;
        return this;
    }

    public Member setPhotoUri(String photoUri) {
        this.photoUri = photoUri;
        return this;
    }

    public String getUid() {
        return uid;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getName() {
        return name;
    }

    public String getPhotoUri() {
        return photoUri;
    }
}
