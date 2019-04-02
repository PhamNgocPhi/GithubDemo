package com.systena.githupdemo.util.network;

import android.content.Context;

import com.systena.githupdemo.util.common.Define;
import com.systena.githupdemo.util.common.DeviceUtil;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class NetworkConnectionInterceptor implements Interceptor {

    private RxBus rxBus;
    private Context context;

    public NetworkConnectionInterceptor(Context context) {
        rxBus = new RxBus();
        this.context = context;
    }

    @Override
    public Response intercept(Chain chain) {
        Request request = chain.request();
        if (!DeviceUtil.isInternetAvailable(context)) {
            rxBus.send(Define.Network.ErrorCode.LOST_INTERNET);
        } else {
            try {
                Response response = chain.proceed(request);
                switch (response.code()) {
                    case 503:
                        rxBus.send(Define.Network.ErrorCode.NO_RESPONSE);
                        break;
                }
                return response;

            } catch (IOException e) {
                rxBus.send(Define.Network.ErrorCode.NO_RESPONSE);
            }
        }
        return null;
    }
}
