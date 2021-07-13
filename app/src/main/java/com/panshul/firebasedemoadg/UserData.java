package com.panshul.firebasedemoadg;

public class UserData {
    String email;
    String uid;
    String phone;
    String name;

    public UserData(){

    }

    public UserData(String email, String uid, String phone, String name) {
        this.email = email;
        this.uid = uid;
        this.phone = phone;
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
