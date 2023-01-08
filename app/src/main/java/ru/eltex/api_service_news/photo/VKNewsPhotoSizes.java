package ru.eltex.api_service_news.photo;

/**
 * Изображения разных размеров
 */
public class VKNewsPhotoSizes {

    Integer height;
    String type;
    Integer width;
    String url;

    public VKNewsPhotoSizes() {
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
