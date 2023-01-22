package ru.eltex.adapters.news.content;

import ru.eltex.adapters.news.PostContentAdapter;
import ru.eltex.api_service.news.body.items.VKNewsAttachments;

/**
 * Интерфейс который реализуют классы для получения контента в зависимости от его типа
 */
public interface IContent {
    String getContent(VKNewsAttachments vkNewsAttachments, PostContentAdapter.ViewHolder holder);
}
