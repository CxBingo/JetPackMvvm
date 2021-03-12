package com.bingo.basemodule.network.bean;

/**
 * Created by francisbingo on 2020/10/26 14:41
 * 与服务端规定的返回协议规则
 */

public class BaseBean<T> {

    /**
     * {
     *         "code": 200,
     *             "msg": "ok",
     *             "data": {
     *         "code": 5394 //测试阶段返回，后续会删除
     *     },
     *         "time": "2020-01-13 17:48:10"
     *     }
     */

    private int code;
    private String msg;
    private String time;
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "BaseBean{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", time='" + time + '\'' +
                ", data=" + data +
                '}';
    }
}
