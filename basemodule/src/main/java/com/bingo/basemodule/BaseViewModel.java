package com.bingo.basemodule;

import android.app.Activity;
import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.bingo.basemodule.network.bean.BaseArrayBean;
import com.bingo.basemodule.network.bean.BaseBean;
import com.bingo.basemodule.network.manager.MsgBean;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


/**
 * Created by francisbingo on 3/12/21 11:28 AM
 */
public abstract class BaseViewModel extends ViewModel {

    protected Activity mContext;
    protected final String TAG = this.getClass().getSimpleName();

    public BaseViewModel() {
        Log.d(TAG, "BaseViewModel: ");
        EventBus.getDefault().register(this);
    }

    public void setContext(Activity mContext) {
        this.mContext = mContext;
    }

    @Override
    protected void onCleared() {
        Log.d(TAG, "onCleared: ");
        super.onCleared();
        if (mContext != null) {
            mContext = null;
        }
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onReceiveMsg(MsgBean bean) {
        if (bean.getArrayBean() != null) {
            onArrayBean(bean.getCmd(), bean.getArrayBean());
        } else if (bean.getBaseBean() != null) {
            onBaseBean(bean.getCmd(), bean.getBaseBean());
        }

    }

    protected abstract void onBaseBean(String cmd, BaseBean baseBean);

    protected abstract void onArrayBean(String cmd, BaseArrayBean baseBean);

}
