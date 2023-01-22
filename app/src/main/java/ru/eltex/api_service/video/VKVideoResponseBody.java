package ru.eltex.api_service.video;

import java.util.List;

import ru.eltex.api_service.news.video.VKNewsVideo;

public class VKVideoResponseBody {

    Integer count;
    List<VKNewsVideo> items;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<VKNewsVideo> getItems() {
        return items;
    }

    public void setItems(List<VKNewsVideo> items) {
        this.items = items;
    }
}
