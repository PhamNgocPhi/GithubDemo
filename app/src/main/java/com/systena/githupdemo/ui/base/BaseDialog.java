package com.systena.githupdemo.ui.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.DialogFragment;

public abstract class BaseDialog<B extends ViewDataBinding>  {

    protected B binding;

    @LayoutRes
    protected abstract int layoutRes();



    public interface DialogListener {
        void onButtonClick();
    }
}
