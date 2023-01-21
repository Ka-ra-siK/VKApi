package ru.eltex.api_service.friends;

import com.google.gson.annotations.SerializedName;

public class VKFriend {
    @SerializedName("last_name")
    String lastName;
    @SerializedName("first_name")
    String firstName;
    @SerializedName("photo_50")
    String photo50;
    @SerializedName("photo_100")
    String photo100;
    @SerializedName("online_mobile")
    String onlineMobile;
    String online;
    String sex;
    String id;

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
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
