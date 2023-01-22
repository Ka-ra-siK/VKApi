package ru.eltex.adapters.news.content;

import ru.eltex.api_service.news.body.items.VKNewsAttachments;
import ru.eltex.api_service.news.link.VKNewsLink;
import ru.eltex.api_service.news.photo.VKNewsPhotoSizes;

/**
 * Класс реализующий интерфейс IContent котрый должен будет получать контент для статей
 */
public class LinkContent implements IContent {
    @Override
    public String getContent(VKNewsAttachments vkNewsAttachments) {
        VKNewsLink newsLink = vkNewsAttachments.getLink();
        String url = "";
        if (newsLink.getPhoto() != null) {
            for (VKNewsPhotoSizes photoSizes : newsLink.getPhoto().getSizes()) {
                if (photoSizes.getType().equals("o")) {
                    url = photoSizes.getUrl();
                } else if (photoSizes.getType().equals("k") && url.equals("")) {
                    url = photoSizes.getUrl();
                }
            }
        }
        return url;
    }
}
