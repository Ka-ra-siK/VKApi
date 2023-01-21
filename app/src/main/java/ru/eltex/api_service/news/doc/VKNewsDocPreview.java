package ru.eltex.api_service.news.doc;

public class VKNewsDocPreview {
    VKNewsDocPhoto photo;
    VKNewsDocVideo video;

    public VKNewsDocPhoto getPhoto() {
        return photo;
    }

    public void setPhoto(VKNewsDocPhoto photo) {
        this.photo = photo;
    }

    public VKNewsDocVideo getVideo() {
        return video;
    }

    public void setVideo(VKNewsDocVideo video) {
        this.video = video;
    }
}
