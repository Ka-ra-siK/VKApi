package ru.eltex.api_service.news.doc;

import com.google.gson.annotations.SerializedName;


public class VKNewsDoc {
    Integer id;
    @SerializedName("owner_id")
    Integer ownerId;
    String title;
    Integer size;
    String ext;
    Integer type;
    String url;
    VKNewsDocPreview preview;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public VKNewsDocPreview getPreview() {
        return preview;
    }

    public void setPreview(VKNewsDocPreview preview) {
        this.preview = preview;
    }
}
