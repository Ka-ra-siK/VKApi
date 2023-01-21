package ru.eltex.api_service.news.doc;

import com.google.gson.annotations.SerializedName;

public class VKNewsDocVideo {

    String src;
    Integer width;
    Integer height;
    @SerializedName("file_size")
    Integer fileSize;

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getFileSize() {
        return fileSize;
    }

    public void setFileSize(Integer fileSize) {
        this.fileSize = fileSize;
    }
}
