package com.systena.githupdemo.data.model;

import com.google.gson.annotations.SerializedName;
import com.systena.githupdemo.util.common.Define;

public class ApiObjectResponse<T> {

    @SerializedName(Define.Network.BaseResponse.SUCCESS)
    private Integer success;

    @SerializedName(Define.Network.BaseResponse.DATA)
    private T data;

    @SerializedName(Define.Network.BaseResponse.ERROR)
    private RequestError error;

    public Integer getSuccess() {
        return success;
    }

    public boolean isSuccess() {
        return success == 1;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public RequestError getRequestError() {
        return error;
    }

    public void setRequestError(RequestError error) {
        this.error = error;
    }
}
