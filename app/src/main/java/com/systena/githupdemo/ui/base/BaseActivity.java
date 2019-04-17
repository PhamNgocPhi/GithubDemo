package com.systena.githupdemo.ui.base;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.systena.githupdemo.R;
import com.systena.githupdemo.data.model.ApiObjectResponse;
import com.systena.githupdemo.data.model.RequestError;
import com.systena.githupdemo.ui.NavigationManager;
import com.systena.githupdemo.ui.custom.AppDialog;
import com.systena.githupdemo.ui.custom.AppLoading;
import com.systena.githupdemo.util.common.Define;
import com.systena.githupdemo.util.network.RxBus;

import java.io.IOException;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import dagger.android.support.DaggerAppCompatActivity;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;

public abstract class BaseActivity<T extends ViewDataBinding> extends DaggerAppCompatActivity {

    private final long CLICK_TIME_INTERVAL = 600;

    protected T binding;

    private View focusedViewOnActionDown;
    private boolean touchWasInsideFocusedView, hasMove;
    private float rawX, rawY;
    protected CompositeDisposable disposable;
    public long lastClickTime = System.currentTimeMillis();

    @LayoutRes
    protected abstract int layoutRes();

    protected abstract NavigationManager getNavigationManager();

    protected abstract void showDialogLostInternet();

    protected abstract void showDialogUnknownError();

    protected abstract void showDialogTokenExpired();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, layoutRes());
    }

    @Override
    protected void onResume() {
        super.onResume();
        disposable = new CompositeDisposable();
        observeRxBus();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (disposable != null) {
            disposable.dispose();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (disposable != null) {
            disposable.dispose();
        }
    }

    @Override
    public void onBackPressed() {
        if (getNavigationManager() != null && getNavigationManager().getCurrentFragment() != null) {
            if (getNavigationManager().getCurrentFragment().onBackPressed()) {
                super.onBackPressed();
            }
        } else {
            super.onBackPressed();
        }
    }

    // hide key board when click out side
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                rawX = ev.getRawX();
                rawY = ev.getRawY();
                hasMove = false;
                focusedViewOnActionDown = getCurrentFocus();
                if (focusedViewOnActionDown != null) {
                    final Rect rect = new Rect();
                    final int[] coordinates = new int[2];

                    focusedViewOnActionDown.getLocationOnScreen(coordinates);

                    rect.set(coordinates[0], coordinates[1],
                            coordinates[0] + focusedViewOnActionDown.getWidth(),
                            coordinates[1] + focusedViewOnActionDown.getHeight());

                    final int x = (int) ev.getX();
                    final int y = (int) ev.getY();

                    touchWasInsideFocusedView = rect.contains(x, y);
                }
                break;

            case MotionEvent.ACTION_MOVE:
                if (!hasMove) {
                    float delta = (float) Math.hypot(rawX - ev.getRawX(), rawY - ev.getRawY());
                    hasMove = delta > 6f;
                }
                break;
            case MotionEvent.ACTION_UP:
                if (focusedViewOnActionDown != null) {
                    final boolean consumed = super.dispatchTouchEvent(ev);
                    final View currentFocus = getCurrentFocus();
                    if (hasMove) {
                        return consumed;
                    }
                    if (focusedViewOnActionDown.equals(currentFocus)) {
                        if (touchWasInsideFocusedView) {
                            return consumed;
                        }
                    } else if (currentFocus instanceof EditText) {
                        return consumed;
                    }

                    InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputMethodManager.hideSoftInputFromWindow(focusedViewOnActionDown.getWindowToken(), 0);
                    return consumed;
                }
                break;
            default:
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    protected void showLoading() {
        AppLoading.getInstance(this).show();
    }

    protected void hideLoading() {
        AppLoading.getInstance(this).hidden();
    }

    protected void showDialogError(String content, String btnPrimary, boolean isCancel, @Nullable AppDialog.OnDialogClickListener listener) {
        AppDialog appDialog = new AppDialog(this);
        appDialog.setTitle(getString(R.string.error))
                .setContent(content)
                .onClickPositiveButton(btnPrimary, listener);
        if (isCancel) {
            appDialog.setCanceledOnTouchOutside();
        }
        appDialog.show();
    }

    private void observeRxBus() {
        disposable.add(RxBus.getInstance()
                .toObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleRxBusData)
        );
    }

    private void handleRxBusData(Object data) {
        if (data instanceof String) {
            String errorCode = (String) data;
            switch (errorCode) {
                case Define.Network.ErrorCode.LOST_INTERNET:
                    showDialogLostInternet();
                    break;
                case Define.Network.ErrorCode.NO_RESPONSE:
                case Define.Network.ErrorCode.UNKNOWN_ERROR:
                case Define.Network.ErrorCode.TIME_OUT:
                    showDialogUnknownError();
                    break;
                case Define.Network.ErrorCode.ACCESS_TOKEN_EXPIRED:
                    showDialogTokenExpired();
                    break;
                default:
                    showDialogUnknownError();
                    break;
            }
        }
    }

    protected boolean isDuplicateClick() {
        long now = System.currentTimeMillis();
        if (now - lastClickTime < CLICK_TIME_INTERVAL) {
            lastClickTime = now;
            return true;
        } else {
            lastClickTime = now;
            return false;
        }
    }

    protected RequestError handleThrowable(Throwable throwable, boolean isShowDialog) {
        RequestError requestError = new RequestError();
        if (throwable instanceof IOException) {
            requestError.setErrorCode(Define.Network.ErrorCode.LOST_INTERNET);
            requestError.setErrorMessage(throwable.getMessage());
        } else if (throwable instanceof HttpException) {
            HttpException httpException = (HttpException) throwable;
            try {
                String errorBody = httpException.response().errorBody().string();
                Gson gson = new GsonBuilder().create();
                ApiObjectResponse apiResponse = gson.fromJson(errorBody, ApiObjectResponse.class);
                if (apiResponse != null && apiResponse.getRequestError() != null) {
                    requestError = apiResponse.getRequestError();
                } else {
                    requestError.setErrorCode(String.valueOf(httpException.code()));
                    requestError.setErrorMessage("リクエストタイムアウト。");
                }
            } catch (IOException e) {
                requestError.setErrorCode(Define.Network.ErrorCode.UNKNOWN_ERROR);
                requestError.setErrorMessage("リクエストタイムアウト。");
            } catch (IllegalStateException e) {
                requestError.setErrorCode(Define.Network.ErrorCode.UNKNOWN_ERROR);
                requestError.setErrorMessage("リクエストタイムアウト。");
            } catch (JsonSyntaxException e) {
                requestError.setErrorCode(Define.Network.ErrorCode.UNKNOWN_ERROR);
                requestError.setErrorMessage("リクエストタイムアウト。");
            } catch (NullPointerException e) {
                requestError.setErrorCode(Define.Network.ErrorCode.UNKNOWN_ERROR);
                requestError.setErrorMessage("リクエストタイムアウト。");
            }
        }

        return requestError;
    }
}
