package ru.eltex.api_service_news.body.items;

import com.google.gson.annotations.SerializedName;

/**
 * Ифнормация о лайках в записи
 */
public class VKNewsLikes {

    Integer count;
    @SerializedName("user_likes")
    Integer userLikes;
    @SerializedName("can_like")
    Integer canLike;
    @SerializedName("can_publish")
    Integer canPublish;

    public VKNewsLikes() {
    }

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

    public Integer getCanLike() {
        return canLike;
    }

    public void setCanLike(Integer canLike) {
        this.canLike = canLike;
    }

    public Integer getCanPublish() {
        return canPublish;
    }

    public void setCanPublish(Integer canPublish) {
        this.canPublish = canPublish;
    }
}
