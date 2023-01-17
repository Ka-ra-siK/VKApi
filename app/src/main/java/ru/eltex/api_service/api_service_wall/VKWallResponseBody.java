package ru.eltex.api_service.api_service_wall;

import java.util.List;

import ru.eltex.api_service.api_service_wall.items.VKWallItems;

public class VKWallResponseBody {
    String count;
    List<VKWallItems> item;

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public List<VKWallItems> getItem() {
        return item;
    }

    public void setItem(List<VKWallItems> item) {
        this.item = item;
    }
}
