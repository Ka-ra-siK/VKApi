package ru.eltex.api_service.api_service_wall.items.attachments;

public class VKWallItemsAttachments {

    String type;
    VKWallItemsAttachmentsPhoto vkWallPhoto;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public VKWallItemsAttachmentsPhoto getVkWallPhoto() {
        return vkWallPhoto;
    }

    public void setVkWallPhoto(VKWallItemsAttachmentsPhoto vkWallPhoto) {
        this.vkWallPhoto = vkWallPhoto;
    }
}
