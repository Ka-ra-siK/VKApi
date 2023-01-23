package ru.eltex.api_service.user.photo;

import com.google.gson.annotations.SerializedName;

public class PhotoItemsComments {
    @SerializedName("count")
    Integer count;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
