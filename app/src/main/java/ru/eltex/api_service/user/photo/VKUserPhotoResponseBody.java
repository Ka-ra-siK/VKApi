package ru.eltex.api_service.user.photo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class VKUserPhotoResponseBody {
    @SerializedName("count")
    @Expose
    Integer count;
    @SerializedName("items")
    @Expose
    List<VKUserPhotoResponseItems> photoResponseItems;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<VKUserPhotoResponseItems> getPhotoResponseItems() {
        return photoResponseItems;
    }

    public void setPhotoResponseItems(List<VKUserPhotoResponseItems> photoResponseItems) {
        this.photoResponseItems = photoResponseItems;
    }
}
