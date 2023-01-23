package ru.eltex.api_service.user.photo;

import com.google.gson.annotations.SerializedName;

public class PhotoItemsLikes {
    @SerializedName("count")
    Integer count;
    @SerializedName("user_likes")
    Integer userLikes;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getUserLikes() {
        return userLikes;
    }

    public void setUserLikes(Integer userLikes) {
        this.userLikes = userLikes;
    }
}
