package ru.eltex.api_service.api_service_news.body.items;

import com.google.gson.annotations.SerializedName;

/**
 * Информация о репостах записи
 */
public class VKNewsReposts {

    Integer count;
    @SerializedName("user_reposted")
    Integer userReposted;

    public VKNewsReposts() {
    }

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
