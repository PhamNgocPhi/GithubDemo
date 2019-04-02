package com.systena.githupdemo.util.common;

import android.content.Context;

import javax.inject.Inject;

import androidx.annotation.StringRes;

public class ResourceProvider implements IResourceProvider {
    private Context context;

    @Inject
    public ResourceProvider(Context context) {
        this.context = context;
    }

    public String getString(@StringRes int stringId) {
        return context.getString(stringId);
    }
}
