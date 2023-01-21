package ru.eltex.api_service.news.video;

/**
 * Изображения к видео
 */
public class VKNewsVideoImage {

    String url;
    Integer width;
    Integer height;

    public VKNewsVideoImage() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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
}
