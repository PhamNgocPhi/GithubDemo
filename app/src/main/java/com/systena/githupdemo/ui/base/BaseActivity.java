package com.systena.githupdemo.ui.base;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.systena.githupdemo.GithubApplication;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import dagger.android.support.DaggerAppCompatActivity;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public abstract class BaseActivity<T extends ViewDataBinding> extends DaggerAppCompatActivity {

    private final long CLICK_TIME_INTERVAL = 600;

    protected T binding;

    private View focusedViewOnActionDown;
    private boolean touchWasInsideFocusedView, hasMove;
    private float rawX, rawY;
    private Disposable disposable;
    public static long lastClickTime = System.currentTimeMillis();

    @LayoutRes
    protected abstract int layoutRes();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, layoutRes());
        ObserveRxBus();
    }

    private void ObserveRxBus() {
        disposable = ((GithubApplication) getApplication())
                .getRxBus()
                .toObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(data -> {

                });
    }

    protected boolean isDuplicateClick() {
        long now = System.currentTimeMillis();
        if (now - lastClickTime < CLICK_TIME_INTERVAL) {
            return true;
        }
        lastClickTime = now;
        return false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (disposable != null) {
            disposable.dispose();
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
}
