package com.systena.githupdemo.util.common;

import android.graphics.drawable.Drawable;
import android.text.TextUtils;

import com.systena.githupdemo.R;

public class CommonUtils {

    public static int getIconByLanguage(String language) {
        if (TextUtils.isEmpty(language)) {
            return R.drawable.ic_default;
        }
        if(language.equals("Java")) {
            return R.drawable.ic_java;
        }
        if(language.equals("C#")) {
            return R.drawable.ic_c;
        }
        if(language.equals("Kotlin")) {
            return R.drawable.ic_kotlin;
        }
        if(language.equals("Swift")) {
            return R.drawable.ic_swift;
        }
        return R.drawable.ic_default;
    }

}
