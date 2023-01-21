package ru.eltex.api_service.news.doc;

import java.util.List;

public class VKNewsDocPhoto {
    List<VKNewsDocPhotoSizes> sizes;

    public List<VKNewsDocPhotoSizes> getSizes() {
        return sizes;
    }

    public void setSizes(List<VKNewsDocPhotoSizes> sizes) {
        this.sizes = sizes;
    }
}
