package com.bingo.jetpackmvvm.show.view;


import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.bingo.basemodule.BaseActivity;
import com.bingo.jetpackmvvm.R;
import com.bingo.jetpackmvvm.databinding.ActivityShowBinding;
import com.bingo.jetpackmvvm.show.model.ShowModel;
import com.bingo.jetpackmvvm.show.vm.ShowViewModel;

public class ShowActivity extends BaseActivity<ActivityShowBinding> {
    private ShowViewModel viewModel;

    @Override
    protected int initLayout() {
        return R.layout.activity_show;
    }

    @Override
    protected void initView() {
        viewModel = ViewModelProviders.of(this).get(ShowViewModel.class);
        viewModel.getTips();

    }

    @Override
    protected void initObserve() {
        viewModel.getLiveData().observe(this, new Observer<ShowModel>() {
            @Override
            public void onChanged(ShowModel showModel) {
                mbinding.showTv.setText(showModel.getResult());
            }
        });
    }


}
