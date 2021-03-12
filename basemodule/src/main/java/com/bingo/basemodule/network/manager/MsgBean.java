package com.bingo.basemodule.network.manager;

import com.bingo.basemodule.network.bean.BaseArrayBean;
import com.bingo.basemodule.network.bean.BaseBean;


/**
 * Created by francisbingo on 3/12/21 11:32 AM
 * 用于EventBus事件分发
 */
public class MsgBean {

    private String cmd;
    private BaseBean baseBean;
    private BaseArrayBean arrayBean;

    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    public BaseBean getBaseBean() {
        return baseBean;
    }

    public void setBaseBean(BaseBean baseBean) {
        this.baseBean = baseBean;
    }

    public BaseArrayBean getArrayBean() {
        return arrayBean;
    }

    public void setArrayBean(BaseArrayBean arrayBean) {
        this.arrayBean = arrayBean;
    }
}
