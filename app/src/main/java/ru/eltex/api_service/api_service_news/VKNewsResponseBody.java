package ru.eltex.api_service.api_service_news;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import ru.eltex.api_service.api_service_news.body.groups.VKNewsGroups;
import ru.eltex.api_service.api_service_news.body.items.VKNewsItems;
import ru.eltex.api_service.api_service_news.body.profiles.VKNewsProfiles;

public class VKNewsResponseBody {

    List<VKNewsItems> items;
    List<VKNewsProfiles> profiles;
    List<VKNewsGroups> groups;
    @SerializedName("next_from")
    String nextFrom;

    public VKNewsResponseBody() {
    }

    public List<VKNewsItems> getItems() {
        return items;
    }

    public void setItems(List<VKNewsItems> items) {
        this.items = items;
    }

    public String getNextFrom() {
        return nextFrom;
    }

    public void setNextFrom(String nextFrom) {
        this.nextFrom = nextFrom;
    }

    public List<VKNewsProfiles> getProfiles() {
        return profiles;
    }

    public void setProfiles(List<VKNewsProfiles> profiles) {
        this.profiles = profiles;
    }

    public List<VKNewsGroups> getGroups() {
        return groups;
    }

    public void setGroups(List<VKNewsGroups> groups) {
        this.groups = groups;
    }
}
