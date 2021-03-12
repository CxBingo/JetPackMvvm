package com.bingo.basemodule.network.impl;

import com.bingo.basemodule.network.bean.BaseBean;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Description：
 * Created by billy_Cui.
 * Date: 2021/1/5
 */
public interface LoginService {

    /**
     * 获取验证码
     *
     * @param request
     * @return
     */
    @POST("/sso/sms/send")
    Observable<BaseBean> getVerifyCode(@Body RequestBody request);

    @POST("/sso/sms/login")
    Observable<BaseBean> loginByVerifyCode(@Body RequestBody request);

    //忘记密码发送验证码
    @POST("/sso/reset/password/sms/send")
    Observable<BaseBean> forgetPwdVerifyCode(@Body RequestBody request);

    //忘记密码-重置密码
    @POST("/sso/reset/password")
    Observable<BaseBean> resetPwd(@Body RequestBody request);

    @POST("/sso-v2/root/login")
    Observable<BaseBean> loginByOneKey(@Body RequestBody request);

    //密码登录
    @POST("/sso-v2/account/login")
    Observable<BaseBean> loginByAccount(@Body RequestBody request);

    @POST("/sso/login/company/list")
    Observable<BaseBean> getCompanyList(@Body RequestBody request);



    /**
     * 帐号密码登陆
     *
     * @param request
     * @return
     */
    @POST("/sso/staff/login")
    Observable<BaseBean> accountLogin(@Body RequestBody request);

    /**
     * @return 获取用户信息
     */
    @GET("app-staff/center/info")
    Observable<BaseBean> getUserInfo();

    @GET("/sso/staff/logout")
    Observable<BaseBean> exitApp();

}
