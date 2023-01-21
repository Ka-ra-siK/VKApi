package ru.eltex.api_service.groups;

import java.util.List;

public class VKGroupsResponseBody {
    Integer count;
    List<VKGroup> items;

    public VKGroupsResponseBody() {
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<VKGroup> getItems() {
        return items;
    }

    public void setItems(List<VKGroup> items) {
        this.items = items;
    }
}
