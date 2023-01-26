package ru.eltex.api_service.news.errors;

import com.google.gson.annotations.SerializedName;

public class VKError {

    @SerializedName("error_code")
    Integer errorCode;
    @SerializedName("error_msg")
    String ErrorMsg;

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return ErrorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        ErrorMsg = errorMsg;
    }
}
