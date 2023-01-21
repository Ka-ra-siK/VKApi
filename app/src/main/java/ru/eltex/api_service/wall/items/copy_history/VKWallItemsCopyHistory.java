package ru.eltex.api_service.wall.items.copy_history;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import ru.eltex.api_service.wall.items.attachments.VKWallItemsAttachments;

public class VKWallItemsCopyHistory {

    @SerializedName("from_id")
    Integer fromId;
    @SerializedName("owner_id")
    Integer ownerId;
    @SerializedName("post_source")
    String postSource;
    @SerializedName("post_type")
    String postType;
    String text;
    Integer id;
    Integer date;
    List<VKWallItemsAttachments> vkWallItemsAttachments;
    CopyHistoryPostSource copyHistoryPostSource;

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

    public String getPostSource() {
        return postSource;
    }

    public void setPostSource(String postSource) {
        this.postSource = postSource;
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

    public List<VKWallItemsAttachments> getVkWallItemsAttachments() {
        return vkWallItemsAttachments;
    }

    public void setVkWallItemsAttachments(List<VKWallItemsAttachments> vkWallItemsAttachments) {
        this.vkWallItemsAttachments = vkWallItemsAttachments;
    }

    public CopyHistoryPostSource getCopyHistoryPostSource() {
        return copyHistoryPostSource;
    }

    public void setCopyHistoryPostSource(CopyHistoryPostSource copyHistoryPostSource) {
        this.copyHistoryPostSource = copyHistoryPostSource;
    }
}
