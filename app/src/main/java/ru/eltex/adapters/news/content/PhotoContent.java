package ru.eltex.adapters.news.content;

import ru.eltex.api_service.news.body.items.VKNewsAttachments;
import ru.eltex.api_service.news.photo.VKNewsPhotoSizes;

/**
 * Класс реализующий интерфейс IContent для получения адреса изображения
 */
public class PhotoContent implements IContent {


    @Override
    public String getContent(VKNewsAttachments vkNewsAttachments) {
        String url = "";
        for (VKNewsPhotoSizes photoSizes : vkNewsAttachments.getPhoto().getSizes()) {
            if (photoSizes.getType().equals("z")) {
                url = photoSizes.getUrl();
            } else if (photoSizes.getType().equals("x") && url.equals("")) {
                url = photoSizes.getUrl();
            }
        }
        return url;
    }
}
