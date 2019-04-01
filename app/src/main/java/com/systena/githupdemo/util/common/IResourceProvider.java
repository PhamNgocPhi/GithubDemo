package com.systena.githupdemo.util.common;

import androidx.annotation.StringRes;

public interface IResourceProvider {
    String getString(@StringRes final int id);
}
