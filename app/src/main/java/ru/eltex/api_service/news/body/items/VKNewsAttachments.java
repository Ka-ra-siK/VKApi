package ru.eltex.api_service.news.body.items;

import ru.eltex.api_service.news.doc.VKNewsDoc;
import ru.eltex.api_service.news.link.VKNewsLink;
import ru.eltex.api_service.news.photo.VKNewsPhoto;
import ru.eltex.api_service.news.video.VKNewsVideo;

/**
 * Информация о контненте содержащемся в записи
 */
public class VKNewsAttachments {

    String type;
    VKNewsPhoto photo;
    VKNewsVideo video;
    VKNewsDoc doc;
    VKNewsLink link;

    public VKNewsAttachments() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public VKNewsPhoto getPhoto() {
        return photo;
    }

    public void setPhoto(VKNewsPhoto photo) {
        this.photo = photo;
    }

    public VKNewsVideo getVideo() {
        return video;
    }

    public void setVideo(VKNewsVideo video) {
        this.video = video;
    }

    public VKNewsDoc getDoc() {
        return doc;
    }

    public void setDoc(VKNewsDoc doc) {
        this.doc = doc;
    }

    public VKNewsLink getLink() {
        return link;
    }

    public void setLink(VKNewsLink link) {
        this.link = link;
    }
}
