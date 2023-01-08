package ru.eltex.api_service_news.body.items;

import com.google.gson.annotations.SerializedName;

/**
 * Информация о комментариях к записи
 */
public class VKNewsComments {

    Integer count;
    @SerializedName("can_post")
    Integer canPost;

    public VKNewsComments() {
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getCanPost() {
        return canPost;
    }

    public void setCanPost(Integer canPost) {
        this.canPost = canPost;
    }
}
