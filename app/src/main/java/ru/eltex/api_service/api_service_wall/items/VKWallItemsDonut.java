package ru.eltex.api_service.api_service_wall.items;

import com.google.gson.annotations.SerializedName;

public class VKWallItemsDonut {

    @SerializedName("is_donut")
    Boolean isDonut;

    public Boolean getDonut() {
        return isDonut;
    }

    public void setDonut(Boolean donut) {
        isDonut = donut;
    }
}
