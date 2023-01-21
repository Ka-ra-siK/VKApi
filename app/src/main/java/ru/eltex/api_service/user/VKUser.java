package ru.eltex.api_service.user;

import android.annotation.SuppressLint;
import android.util.Log;

import com.google.gson.annotations.SerializedName;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
    @SerializedName("online_mobile")
    String onlineMobile;
    String online;
    String status;
    String sex;
//    VKUserCity vkUserCity;

    final static String inputFullDate = "d.M.yyyy";
    final static String inputShortDate = "d.M";
    final static String outputFullDate = "dd.MM.yyyy";
    final static String outputShortDate = "dd.MM";

    @SuppressLint("SimpleDateFormat")
    DateFormat inputFull = new SimpleDateFormat(inputFullDate);
    @SuppressLint("SimpleDateFormat")
    DateFormat outputFull = new SimpleDateFormat(outputFullDate);


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
        if (birthDate != null && (birthDate.matches("\\d\\.\\d{2}") ||
                birthDate.matches("\\d\\.\\d") ||
                birthDate.matches("\\d\\.\\d\\.\\d{4}") ||
                birthDate.matches("\\d\\.\\d{2}\\.\\d{4}")))
        {
            Log.d("birthdate", "regex1");
            birthDate = "0" + birthDate;
        }
        if (birthDate != null && (birthDate.matches("\\d{2}\\.\\d") ||
                birthDate.matches("\\d{2}\\.\\d\\.\\d{4}")))
        {
            Log.d("birthdate", "regex2");
            String[] strArray = null;
            String newBirthDate = "";
            strArray = birthDate.split("\\.");
            strArray[1] = "0" + strArray[1];
             for (int i = 0; i < strArray.length; i++) {
                 if (i <= 1 && i != strArray.length-1)
                 {
                     newBirthDate += strArray[i] + ".";
                 } else {
                     newBirthDate += strArray[i];
                 }
             }
             birthDate = newBirthDate;
        }
        return birthDate;
    }

    public void setBirthDate(String birthDate){
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

    //    public VKUserCity getVkUserCity() {
//        return vkUserCity;
//    }
//
//    public void setVkUserCity(VKUserCity vkUserCity) {
//        this.vkUserCity = vkUserCity;
//    }
}
