package ru.eltex.adapters.news.content;

import ru.eltex.api_service.news.body.items.VKNewsAttachments;
import ru.eltex.api_service.news.doc.VKNewsDoc;
import ru.eltex.api_service.news.doc.VKNewsDocPhoto;
import ru.eltex.api_service.news.doc.VKNewsDocPhotoSizes;

/**
 * Класс реализующий интерфейс IContent для получения контента отмеченого как doc
 * 1 — текстовые документы;
 * 2 — архивы;
 * 3 — gif;
 * 4 — изображения;
 * 5 — аудио;
 * 6 — видео;
 * 7 — электронные книги;
 * 8 — неизвестно.
 */
public class DocContent implements IContent {
    @Override
    public String getContent(VKNewsAttachments vkNewsAttachments) {
        String url = "";
        VKNewsDoc newsDoc = vkNewsAttachments.getDoc();
        if (newsDoc.getType() == 3) {
            VKNewsDocPhoto newsPhoto = newsDoc.getPreview().getPhoto();
            for (VKNewsDocPhotoSizes sizes : newsPhoto.getSizes()) {
                if (sizes.getType().equals("x")) {
                    url = sizes.getSrc();
                } else if (url.equals("") && sizes.equals("o")) {
                    url = sizes.getSrc();
                }
            }
        }
        return url;
    }
}
