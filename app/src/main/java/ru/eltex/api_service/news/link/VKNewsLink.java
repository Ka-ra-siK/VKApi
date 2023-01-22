package ru.eltex.api_service.news.link;

import ru.eltex.api_service.news.photo.VKNewsPhoto;

public class VKNewsLink {

    String url;
    String description;
    VKNewsPhoto photo;
    String title;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public VKNewsPhoto getPhoto() {
        return photo;
    }

    public void setPhoto(VKNewsPhoto photo) {
        this.photo = photo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
