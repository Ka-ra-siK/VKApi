package ru.eltex.api_service.user;

import com.google.gson.annotations.SerializedName;

public class VKUser {
    @SerializedName("last_name")
    String lastName;
    @SerializedName("first_name")
    String firstName;
    @SerializedName("bdate")
    String birthDate;
    @SerializedName("home_town")
    String homeTown;
    @SerializedName("photo_100")
    String photo100;
    String status;
    String sex;
//    VKUserCity vkUserCity;

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

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getHomeTown() {
        return homeTown;
    }

    public void setHomeTown(String homeTown) {
        this.homeTown = homeTown;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhoto100() {
        return photo100;
    }

    public void setPhoto100(String photo100) {
        this.photo100 = photo100;
    }

//    public VKUserCity getVkUserCity() {
//        return vkUserCity;
//    }
//
//    public void setVkUserCity(VKUserCity vkUserCity) {
//        this.vkUserCity = vkUserCity;
//    }
}
