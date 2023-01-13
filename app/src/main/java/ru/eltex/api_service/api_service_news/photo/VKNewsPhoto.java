package ru.eltex.api_service.api_service_news.photo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Информация о изображениях в записи
 */
public class VKNewsPhoto {

    @SerializedName("album_id")
    Integer albumID;
    Integer date;
    Integer id;
    @SerializedName("owner_id")
    Integer ownerID;
    List<VKNewsPhotoSizes> sizes;

    public VKNewsPhoto() {
    }

    public Integer getAlbumID() {
        return albumID;
    }

    public void setAlbumID(Integer albumID) {
        this.albumID = albumID;
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

    public Integer getOwnerID() {
        return ownerID;
    }

    public void setOwnerID(Integer ownerID) {
        this.ownerID = ownerID;
    }

    public List<VKNewsPhotoSizes> getSizes() {
        return sizes;
    }

    public void setSizes(List<VKNewsPhotoSizes> sizes) {
        this.sizes = sizes;
    }
}
