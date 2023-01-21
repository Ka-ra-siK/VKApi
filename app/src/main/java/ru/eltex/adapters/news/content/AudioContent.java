package ru.eltex.adapters.news.content;

import ru.eltex.api_service.news.body.items.VKNewsAttachments;

/**
 * Класс реализующий интерфейс IContent котрый должен будет получать контент для аудио
 */
public class AudioContent implements IContent {

    @Override
    public String getContent(VKNewsAttachments vkNewsAttachments) {
        return null;
    }
}
