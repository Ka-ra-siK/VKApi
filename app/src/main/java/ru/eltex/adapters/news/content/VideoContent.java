package ru.eltex.adapters.news.content;

import ru.eltex.api_service.news.body.items.VKNewsAttachments;
import ru.eltex.api_service.news.video.VKNewsVideoImage;

/**
 * Класс реализующий интерфейс IContent для получения адреса изображения предпоказа видео
 */
public class VideoContent implements IContent {

    public VideoContent() {}

    @Override
    public String getContent(VKNewsAttachments vkNewsAttachments) {
        int sizeNewsVideoImage = vkNewsAttachments.getVideo().getImage().size();
        VKNewsVideoImage newsVideoImage = vkNewsAttachments.getVideo().getImage().get(sizeNewsVideoImage - 1);
        return newsVideoImage.getUrl();
    }
}
