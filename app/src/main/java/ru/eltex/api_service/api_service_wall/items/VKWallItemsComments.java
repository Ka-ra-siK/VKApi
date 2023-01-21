package ru.eltex.api_service.wall.items;

import com.google.gson.annotations.SerializedName;

public class VKWallItemsComments {

    @SerializedName("can_post")
    Integer canPost;
    @SerializedName("groups_can_post")
    Boolean groupsCanPost;
    Integer count;

    public Integer getCanPost() {
        return canPost;
    }

    public void setCanPost(Integer canPost) {
        this.canPost = canPost;
    }

    public Boolean getGroupsCanPost() {
        return groupsCanPost;
    }

    public void setGroupsCanPost(Boolean groupsCanPost) {
        this.groupsCanPost = groupsCanPost;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
