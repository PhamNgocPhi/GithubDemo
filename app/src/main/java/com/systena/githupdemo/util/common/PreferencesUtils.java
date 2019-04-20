package com.systena.githupdemo.util.common;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Inject;

public class PreferencesUtils implements IPreferencesUtils {

    private SharedPreferences mPreferences;

    @Inject
    public PreferencesUtils(Context context) {
        mPreferences = context.getSharedPreferences(Define.Preference.PREF_FILE_NAME, Context.MODE_PRIVATE);
    }

    @Override
    public void saveAccessToken(String accessToken) {
        try {
            String token = AESUtils.encrypt(accessToken);
            mPreferences.edit().putString(Define.Preference.KEY_ACCESS_TOKEN, token).apply();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getAccessToken() {
        String token = mPreferences.getString(Define.Preference.KEY_ACCESS_TOKEN, "");
        try {
            return AESUtils.decrypt(token);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
