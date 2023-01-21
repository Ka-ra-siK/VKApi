package ru.eltex.api_service.user.photo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PhotoResponseItemsSizes {
    @SerializedName("height")
    @Expose
    Integer height;
    @SerializedName("width")
    @Expose
    Integer width;
    @SerializedName("type")
    @Expose
    String type;
    @SerializedName("url")
    @Expose
    String url;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
