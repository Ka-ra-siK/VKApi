package ru.eltex.api_service.api_service_wall.items;

import com.google.gson.annotations.SerializedName;

public class VKWallItemsReposts {
    Integer count;
    @SerializedName("user_reposted")
    Integer userReposted;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getUserReposted() {
        return userReposted;
    }

    public void setUserReposted(Integer userReposted) {
        this.userReposted = userReposted;
    }
}
