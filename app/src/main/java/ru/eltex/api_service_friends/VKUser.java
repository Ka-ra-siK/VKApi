package ru.eltex.api_service_friends;

import com.google.gson.annotations.SerializedName;

public class VKUser {
    @SerializedName("last_name")
    String lastName;
    @SerializedName("first_name")
    String firstName;

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
}
