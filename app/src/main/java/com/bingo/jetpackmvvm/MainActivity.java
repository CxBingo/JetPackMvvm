package com.bingo.jetpackmvvm;

import android.content.Intent;
import android.view.View;

import com.bingo.basemodule.BaseActivity;
import com.bingo.jetpackmvvm.databinding.ActivityMainBinding;
import com.bingo.jetpackmvvm.show.view.ShowActivity;

public class MainActivity extends BaseActivity<ActivityMainBinding> {


    @Override
    protected int initLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        mbinding.navigaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ShowActivity.class));
            }
        });
    }

    @Override
    protected void initObserve() {

    }
}
