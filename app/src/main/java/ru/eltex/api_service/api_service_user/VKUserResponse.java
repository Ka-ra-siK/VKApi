package ru.eltex.api_service.api_service_user;

import java.util.List;

public class VKUserResponse {
    List<VKUser> response;

    public List<VKUser> getResponse() {
        return response;
    }

    public void setResponse(List<VKUser> response) {
        this.response = response;
    }
}
