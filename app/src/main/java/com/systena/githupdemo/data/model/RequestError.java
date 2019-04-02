package com.systena.githupdemo.data.model;

import com.google.gson.annotations.SerializedName;
import com.systena.githupdemo.util.common.Define;

public class RequestError {
    @SerializedName(Define.Network.BaseResponse.ERROR_CODE)
    private String errorCode;

    @SerializedName(Define.Network.BaseResponse.ERROR_MESSAGE)
    private String errorMessage;

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
