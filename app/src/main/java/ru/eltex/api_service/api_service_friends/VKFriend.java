package ru.eltex.api_service.api_service_friends;

import com.google.gson.annotations.SerializedName;

public class VKFriend {
    @SerializedName("last_name")
    String lastName;
    @SerializedName("first_name")
    String firstName;
    String sex;

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
}
