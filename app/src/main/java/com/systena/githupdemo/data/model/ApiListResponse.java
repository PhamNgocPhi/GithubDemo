package com.systena.githupdemo.data.model;

import com.google.gson.annotations.SerializedName;
import com.systena.githupdemo.util.common.Define;

import java.util.List;

public class ApiListResponse<T> {
    @SerializedName(Define.Network.BaseResponse.SUCCESS)
    private Integer success;

    @SerializedName(Define.Network.BaseResponse.DATA)
    private List<T> data;

    @SerializedName(Define.Network.BaseResponse.PAGE)
    private Integer page;

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

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public RequestError getRequestError() {
        return error;
    }

    public void setRequestError(RequestError error) {
        this.error = error;
    }
}
