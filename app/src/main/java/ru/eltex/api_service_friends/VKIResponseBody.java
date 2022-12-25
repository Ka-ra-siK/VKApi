package ru.eltex.api_service_friends;

import java.util.List;

public class VKIResponseBody {
    Integer count;
    List<VKUser> items;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<VKUser> getItems() {
        return items;
    }

    public void setItems(List<VKUser> items) {
        this.items = items;
    }
}
