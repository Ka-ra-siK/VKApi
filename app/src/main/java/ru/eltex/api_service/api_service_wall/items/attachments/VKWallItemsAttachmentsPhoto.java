package ru.eltex.api_service.wall.items.attachments;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class VKWallItemsAttachmentsPhoto {

    @SerializedName("album_id")
    Integer albumId;
    @SerializedName("owner_id")
    Integer ownerId;
    @SerializedName("post_id")
    Integer postId;
    @SerializedName("square_crop")
    String squareCrop;
    String text;
    Integer id;
    Integer date;
    @SerializedName("has_tags")
    Boolean hasTags;
    List<AttachmentsPhotoSize> attachmentsPhotoSizes;

    public Integer getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Integer albumId) {
        this.albumId = albumId;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public String getSquareCrop() {
        return squareCrop;
    }

    public void setSquareCrop(String squareCrop) {
        this.squareCrop = squareCrop;
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

    public Boolean getHasTags() {
        return hasTags;
    }

    public void setHasTags(Boolean hasTags) {
        this.hasTags = hasTags;
    }

    public List<AttachmentsPhotoSize> getAttachmentsPhotoSizes() {
        return attachmentsPhotoSizes;
    }

    public void setAttachmentsPhotoSizes(List<AttachmentsPhotoSize> attachmentsPhotoSizes) {
        this.attachmentsPhotoSizes = attachmentsPhotoSizes;
    }
}
