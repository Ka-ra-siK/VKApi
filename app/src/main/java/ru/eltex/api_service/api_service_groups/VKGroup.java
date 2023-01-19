package ru.eltex.api_service.api_service_groups;

import com.google.gson.annotations.SerializedName;

public class VKGroup {
    Integer id;
    @SerializedName("members_count")
    Integer membersCount;
    String name;
    String type;
    @SerializedName("photo_50")
    String photo50;
    @SerializedName("photo_100")
    String photo100;
    @SerializedName("photo_200")
    String photo200;

    public VKGroup() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMembersCount() {
        return membersCount;
    }

    public void setMembersCount(Integer membersCount) {
        this.membersCount = membersCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getPhoto200() {
        return photo200;
    }

    public void setPhoto200(String photo200) {
        this.photo200 = photo200;
    }
}
