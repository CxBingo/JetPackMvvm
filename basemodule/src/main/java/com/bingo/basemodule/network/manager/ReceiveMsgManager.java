package com.bingo.basemodule.network.manager;

import com.bingo.basemodule.network.bean.BaseArrayBean;
import com.bingo.basemodule.network.bean.BaseBean;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by francisbingo on 2020/10/26 14:32
 */

public class ReceiveMsgManager {
    private static ReceiveMsgManager manager;

    private NetFailCallBack netFailCallBack;

    public void setNetFailCallBack(NetFailCallBack netFailCallBack) {
        this.netFailCallBack = netFailCallBack;
    }

    public static ReceiveMsgManager getInstance() {
        return manager == null ? manager = new ReceiveMsgManager() : manager;
    }

    private ReceiveMsgManager() {
    }


    /**
     * 分发消息
     *
     * @param baseBean  Bean基类
     * @param urlOrigin 请求地址
     */
    public void dispatchMessage(BaseBean baseBean, String urlOrigin) {
        if (baseBean.getCode() != 200) {
            if (netFailCallBack != null) {
                netFailCallBack.onFail(baseBean.getCode(), baseBean.getMsg());
            }
        }
        MsgBean msgBean = new MsgBean();
        msgBean.setCmd(urlOrigin);
        msgBean.setBaseBean(baseBean);
        EventBus.getDefault().post(msgBean);
    }


    /**
     * 分发数组消息
     *
     * @param baseBean Bean基类
     * @param cmdStr   请求地址
     */
    public void dispatchMessage(BaseArrayBean baseBean, String cmdStr) {
        MsgBean msgBean = new MsgBean();
        msgBean.setCmd(cmdStr);
        msgBean.setArrayBean(baseBean);
        EventBus.getDefault().post(msgBean);
    }


    /**
     * 网络请求失败回调
     */
    public interface NetFailCallBack {
        void onFail(int code, String msg);
    }


}
