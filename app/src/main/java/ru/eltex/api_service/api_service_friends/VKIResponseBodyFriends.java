package ru.eltex.api_service.api_service_friends;

import java.util.List;

public class VKIResponseBodyFriends {
    Integer count;
    List<VKFriend> items;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<VKFriend> getItems() {
        return items;
    }

    public void setItems(List<VKFriend> items) {
        this.items = items;
    }
}
