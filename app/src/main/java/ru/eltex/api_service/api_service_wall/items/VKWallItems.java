package ru.eltex.api_service.api_service_wall.items;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class VKWallItems {

    Integer id;
    Integer date;
    @SerializedName("from_id")
    Integer fromId;
    @SerializedName("owner_id")
    Integer ownerId;
    @SerializedName("short_text_rate")
    String shortTextRate;
    @SerializedName("post_type")
    String postType;
    String text;
    String hash;
    @SerializedName("is_favorite")
    Boolean isFavorite;
    @SerializedName("post_source")
    VKWallItemsPostSource postSource;
    VKWallItemsDonut donut;
    VKWallItemsComments comments;
    List<VKWallItemsAttachments> attachments;
    VKWallItemsLikes likes;
    VKWallItemsReposts reposts;
    VKWallItemsViews views;
    List<VKWallItemsCopyHistory> copyHistories;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDate() {
        return date;
    }

    public void setDate(Integer date) {
        this.date = date;
    }

    public Integer getFromId() {
        return fromId;
    }

    public void setFromId(Integer fromId) {
        this.fromId = fromId;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public String getShortTextRate() {
        return shortTextRate;
    }

    public void setShortTextRate(String shortTextRate) {
        this.shortTextRate = shortTextRate;
    }

    public String getPostType() {
        return postType;
    }

    public void setPostType(String postType) {
        this.postType = postType;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public Boolean getFavorite() {
        return isFavorite;
    }

    public void setFavorite(Boolean favorite) {
        isFavorite = favorite;
    }

    public VKWallItemsPostSource getPostSource() {
        return postSource;
    }

    public void setPostSource(VKWallItemsPostSource postSource) {
        this.postSource = postSource;
    }

    public VKWallItemsDonut getDonut() {
        return donut;
    }

    public void setDonut(VKWallItemsDonut donut) {
        this.donut = donut;
    }

    public VKWallItemsComments getComments() {
        return comments;
    }

    public void setComments(VKWallItemsComments comments) {
        this.comments = comments;
    }

    public List<VKWallItemsAttachments> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<VKWallItemsAttachments> attachments) {
        this.attachments = attachments;
    }

    public VKWallItemsLikes getLikes() {
        return likes;
    }

    public void setLikes(VKWallItemsLikes likes) {
        this.likes = likes;
    }

    public VKWallItemsReposts getReposts() {
        return reposts;
    }

    public void setReposts(VKWallItemsReposts reposts) {
        this.reposts = reposts;
    }

    public VKWallItemsViews getViews() {
        return views;
    }

    public void setViews(VKWallItemsViews views) {
        this.views = views;
    }

    public List<VKWallItemsCopyHistory> getCopyHistories() {
        return copyHistories;
    }

    public void setCopyHistories(List<VKWallItemsCopyHistory> copyHistories) {
        this.copyHistories = copyHistories;
    }
}
