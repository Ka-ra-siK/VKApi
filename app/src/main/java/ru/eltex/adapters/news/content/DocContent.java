package ru.eltex.adapters.news.content;

import android.net.Uri;
import android.view.ViewGroup;

import ru.eltex.adapters.news.PostContentAdapter;
import ru.eltex.api_service.news.body.items.VKNewsAttachments;
import ru.eltex.api_service.news.doc.VKNewsDoc;

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
    public String getContent(VKNewsAttachments vkNewsAttachments, PostContentAdapter.ViewHolder holder) {
        String url = "";
        VKNewsDoc newsDoc = vkNewsAttachments.getDoc();
        if (newsDoc.getType() == 3) {
            if (newsDoc.getPreview().getVideo() != null) {
                holder.getContentGif().setVideoURI(Uri.parse(newsDoc.getPreview().getVideo().getSrc()));
                holder.getContentGif().setLayoutParams(new ViewGroup.LayoutParams(newsDoc.getPreview().getVideo().getWidth(),
                        newsDoc.getPreview().getVideo().getHeight()));
            } else {
                holder.getContentGif().setVideoURI(Uri.parse(newsDoc.getUrl()));
            }
            holder.getContentGif().start();
        }
        return url;
    }
}
