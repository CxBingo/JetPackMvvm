package com.bingo.basemodule.network;

/**
 * Created by francisbingo on 2020/10/26 14:29
 */

public class BingoConstant {

    public static final String SERVER_URL = "https://www.baidu.com";
    public static final String SERVER_DEV_URL = "http://xxxx";
    public static final String DEVICE_TYPE = "android";
    public static final String FROM_ROLE = "xxx";
    public static final String CHANNEL = "channel";
    // 如果后端改版本此处要修改
    public static final String SDK_VERSION = "1.0";


    public static class ErrorCode {

        public static final int REQUEST_SUCCESS = 200;

        //token不合法
        public static final int TOKEN_ILLEGAL = 10101;


    }

    public static class GmCmd {

        /**
         * 登陆模块
         */
        //获取验证码
        public static final String GET_VERIFY_CODE = "getVerifyCode";
        //忘记密码获取验证码
        public static final String GET_FORGET_VERIFY_CODE = "forgetVerifyCode";
        //重置密码
        public static final String RESET_PWD = "reset_pwd";
        //检测验证码
        public static final String CHECK_FORGET_CODE = "check_forget_code";
        //验证码登陆
        public static final String LOGIN_VERIFY_CODE = "loginByVerifyCode";
        //退出登录
        public static final String EXIT_APP = "exit_app";


    }

    public static class MemberType {
        // 妻子
        public static final String WIFE = "wife";
        // 丈夫
        public static final String HUSBAND = "husband";
        // 父亲
        public static final String FATHER = "father";
        // 母亲
        public static final String MOTHER = "mother";
        // 儿子
        public static final String SON = "son";
        // 女儿
        public static final String DAUGHTER = "daughter";
        // 租客
        public static final String RENTER = "renter";
        //保姆
        public static final String NANNY = "nanny";

    }


}
