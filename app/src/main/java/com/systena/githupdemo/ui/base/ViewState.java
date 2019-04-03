package com.systena.githupdemo.ui.base;

import java.util.List;

public class ViewState {
    private int state;
    private Object objectData;
    private List<Object> listData;

    public ViewState() {
    }

    public ViewState(int state) {
        this.state = state;
    }

    public ViewState(int state, Object objectData, List<Object> listData) {
        this.state = state;
        this.objectData = objectData;
        this.listData = listData;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Object getObjectData() {
        return objectData;
    }

    public void setObjectData(Object objectData) {
        this.objectData = objectData;
    }

    public List<Object> getListData() {
        return listData;
    }

    public void setListData(List<Object> listData) {
        this.listData = listData;
    }
}
