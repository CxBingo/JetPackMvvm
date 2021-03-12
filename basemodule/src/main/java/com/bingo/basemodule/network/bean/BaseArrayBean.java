package com.bingo.basemodule.network.bean;

import java.util.List;

/**
 * Created by francisbingo on 2020/10/26 14:41
 * 与服务端规定的返回协议规则
 */

public class BaseArrayBean<T> {

    /**
     * error_code : 0
     * error_msg : success
     * request_id : e90eda88-5db9-49a5-8b1d-6f737cfa4c3d
     * result : 成功
     * time : 1559285512
     * cmd : login
     * data : {"token":"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1NTkyODkxMTIsImlhdCI6MTU1OTI4NTUxMiwidXNlcm5hbWUiOiIxMzU1MjAwNzE2MyJ9.oYh3640xxFTwzND849LOSu2g9efUaLyLSgwIO88RyJk","from_username":"13552007163"}
     */

    private int error_code;
    private String error_msg;
    private String request_id;
    private String result;
    private Long time;
    private String cmd;
    private List<T> data;

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public String getError_msg() {
        return error_msg;
    }

    public void setError_msg(String error_msg) {
        this.error_msg = error_msg;
    }

    public String getRequest_id() {
        return request_id;
    }

    public void setRequest_id(String request_id) {
        this.request_id = request_id;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    @Override
    public String toString() {
        return "BaseCallModel{" +
                "error_code=" + error_code +
                ", error_msg='" + error_msg + '\'' +
                ", request_id='" + request_id + '\'' +
                ", result='" + result + '\'' +
                ", time=" + time +
                ", cmd='" + cmd + '\'' +
                ", data=" + data +
                '}';
    }
}
