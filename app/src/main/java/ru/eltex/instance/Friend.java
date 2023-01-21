package ru.eltex.instance;

import android.util.Log;

import com.google.gson.annotations.SerializedName;

public class Friend {
    private String id;
    private String firstName;
    private String lastName;
    private String sex;
    @SerializedName("photo_50")
    String photo50;
    @SerializedName("photo_100")
    String photo100;
    String onlineMobile;
    String online;

    public Friend(String id, String firstName, String lastName, String sex, String photo50, String photo100,
                  String online, String onlineMobile) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.sex = sex;
        this.photo50 = photo50;
        this.photo100 = photo100;
        this.online = online;
        this.onlineMobile = onlineMobile;
//        Log.d("ONLINE", this.lastName + " " + this.firstName +
//                " Online: " + this.online + " Online Mobile: " + this.onlineMobile);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhoto50() {
        return photo50;
    }

    public void setPhoto50(String photo50) {
        this.photo50 = photo50;
    }

    public String getPhoto100() {
        return photo100;
    }

    public void setPhoto100(String photo100) {
        this.photo100 = photo100;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOnlineMobile() {
        return onlineMobile;
    }

    public void setOnlineMobile(String onlineMobile) {
        this.onlineMobile = onlineMobile;
    }

    public String getOnline() {
        return online;
    }

    public void setOnline(String online) {
        this.online = online;
    }
}
