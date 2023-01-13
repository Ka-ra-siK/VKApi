package ru.eltex.api_service_news.video;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Информация о видео в записи
 */
public class VKNewsVideo {

    String description;
    List<VKNewsVideoImage> image;
    @SerializedName("first_frame")
    List<VKNewsVideoImage> firstFrame;
    Integer height;
    Integer width;
    Integer id;
    @SerializedName("owner_id")
    Integer ownerID;
    String title;
    @SerializedName("track_code")
    String trackCode;
    String type;

    public VKNewsVideo() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<VKNewsVideoImage> getImage() {
        return image;
    }

    public void setImage(List<VKNewsVideoImage> image) {
        this.image = image;
    }

    public List<VKNewsVideoImage> getFirstFrame() {
        return firstFrame;
    }

    public void setFirstFrame(List<VKNewsVideoImage> firstFrame) {
        this.firstFrame = firstFrame;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTrackCode() {
        return trackCode;
    }

    public void setTrackCode(String trackCode) {
        this.trackCode = trackCode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
