package com.bingo.jetpackmvvm.show.vm;

import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;

import com.bingo.basemodule.BaseViewModel;
import com.bingo.basemodule.network.bean.BaseArrayBean;
import com.bingo.basemodule.network.bean.BaseBean;
import com.bingo.jetpackmvvm.show.model.ShowModel;

/**
 * @author francisbingo on 3/12/21 2:26 PM
 */

public class ShowViewModel extends BaseViewModel {
    private MutableLiveData<ShowModel> liveData = new MediatorLiveData<>();
    private ShowModel showModel;

    public MutableLiveData<ShowModel> getLiveData() {
        if (showModel == null) {
            showModel = new ShowModel();
        }
        return liveData;
    }


    @Override
    protected void onBaseBean(String cmd, BaseBean baseBean) {

    }

    @Override
    protected void onArrayBean(String cmd, BaseArrayBean baseBean) {

    }

    public void getTips() {
        showModel.setResult("就这？？？");
        showModel.setCode(1);
        liveData.postValue(showModel);
    }
}
