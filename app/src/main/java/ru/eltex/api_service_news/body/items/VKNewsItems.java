package ru.eltex.api_service_news.body.items;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Основная информация о зписи
 */
public class VKNewsItems {

    String type;
    @SerializedName("source_id")
    Integer sourceID;
    Integer date;
    Integer id;
    @SerializedName("post_id")
    Integer postID;
    @SerializedName("owner_id")
    Integer ownerID;
    @SerializedName("text")
    String textNewsItem;
    VKNewsComments comments;
    VKNewsLikes likes;
    VKNewsReposts reposts;
    List<VKNewsAttachments> attachments;

    public VKNewsItems() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getSourceID() {
        return sourceID;
    }

    public void setSourceID(Integer sourceID) {
        this.sourceID = sourceID;
    }

    public Integer getDate() {
        return date;
    }

    public void setDate(Integer date) {
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPostID() {
        return postID;
    }

    public void setPostID(Integer postID) {
        this.postID = postID;
    }

    public Integer getOwnerID() {
        return ownerID;
    }

    public void setOwnerID(Integer ownerID) {
        this.ownerID = ownerID;
    }

    public String getTextNewsItem() {
        return textNewsItem;
    }

    public void setTextNewsItem(String textNewsItem) {
        this.textNewsItem = textNewsItem;
    }

    public VKNewsComments getComments() {
        return comments;
    }

    public void setComments(VKNewsComments comments) {
        this.comments = comments;
    }

    public VKNewsLikes getLikes() {
        return likes;
    }

    public void setLikes(VKNewsLikes likes) {
        this.likes = likes;
    }

    public VKNewsReposts getReposts() {
        return reposts;
    }

    public void setReposts(VKNewsReposts reposts) {
        this.reposts = reposts;
    }

    public List<VKNewsAttachments> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<VKNewsAttachments> attachments) {
        this.attachments = attachments;
    }
}
