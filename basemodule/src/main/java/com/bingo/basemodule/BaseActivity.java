package com.bingo.basemodule;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

/**
 * @author francisbingo on 12/28/20 4:02 PM
 * activity基类
 */

public abstract class BaseActivity<T extends ViewDataBinding> extends AppCompatActivity {

    public T mbinding;
    protected final String TAG = this.getClass().getSimpleName();

    public BaseActivity() {
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        设定为竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
        setContentView(initLayout());
        initBinding();
        initView();
        initObserve();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void initBinding() {
        mbinding = DataBindingUtil.setContentView(this, initLayout());
    }


    protected abstract int initLayout();

    protected abstract void initView();

    protected abstract void initObserve();


    public void showKeyboard(EditText editText) {
        editText.requestFocus();
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);
    }

    public void closeKeyboard() {
        View view = getWindow().peekDecorView();
        if (view != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }


}
