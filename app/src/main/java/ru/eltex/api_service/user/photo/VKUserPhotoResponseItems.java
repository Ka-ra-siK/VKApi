package ru.eltex.api_service.user.photo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class VKUserPhotoResponseItems {
    @SerializedName("date")
    @Expose
    Integer date;
    @SerializedName("id")
    @Expose
    Integer id;
    @SerializedName("text")
    @Expose
    String text;
    @SerializedName("album_id")
    @Expose
    Integer albumId;
    @SerializedName("owner_id")
    @Expose
    Integer ownerId;
    @SerializedName("post_id")
    @Expose
    Integer postId;
    @SerializedName("square_crop")
    @Expose
    String squareCrop;
    @SerializedName("has_tags")
    @Expose
    Boolean hasTags;
    @SerializedName("sizes")
    @Expose
    List<PhotoResponseItemsSizes> photoResponseItemsSizesList;

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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

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

    public Boolean getHasTags() {
        return hasTags;
    }

    public void setHasTags(Boolean hasTags) {
        this.hasTags = hasTags;
    }

    public List<PhotoResponseItemsSizes> getPhotoResponseItemsSizesList() {
        return photoResponseItemsSizesList;
    }

    public void setPhotoResponseItemsSizesList(List<PhotoResponseItemsSizes> photoResponseItemsSizesList) {
        this.photoResponseItemsSizesList = photoResponseItemsSizesList;
    }
}
