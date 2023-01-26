package ru.eltex.api_service.video;

import ru.eltex.api_service.news.errors.VKError;

public class VKVideoResponse {
    VKVideoResponseBody response;
    VKError error;

    public VKVideoResponseBody getResponse() {
        return response;
    }

    public void setResponse(VKVideoResponseBody response) {
        this.response = response;
    }

    public VKError getError() {
        return error;
    }

    public void setError(VKError error) {
        this.error = error;
    }
}
