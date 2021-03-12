package com.bingo.basemodule.network.impl;

import com.bingo.basemodule.network.bean.BaseBean;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by francisbingo on 2020/10/26 14:30
 */

public interface RetrofitService {

    @POST("app-staff/schedule/plan/report")
    Observable<BaseBean> getCheckPlan(@Body RequestBody requestBody);


    @POST("app-staff/schedule/report")
    Observable<BaseBean> getScheduleReport(@Body RequestBody requestBody);


    @POST("app-staff/group/distance")
    Observable<BaseBean> getLocationInfo(@Body RequestBody request);

    @POST("app-staff/schedule/clock/list")
    Observable<BaseBean> getDayWorkInfo(@Body RequestBody request);

    @POST("app-staff/schedule/clock/add")
    Observable<BaseBean> getClockCard(@Body RequestBody request);

    @POST("app-staff/group/list")
    Observable<BaseBean> getEmployeeHouse(@Header("companyId") int companyId);

    @GET("/pubsrv/config/get")
    Observable<BaseBean> getConfig();

    @POST("app-staff/message/list")
    Observable<BaseBean> getOrderMsg(@Body RequestBody request);

    @GET("app-staff/message/get")
    Observable<BaseBean> getMsgDetail(@Query("id") int msgid);

    @POST("app-staff/message/read")
    Observable<BaseBean> readMsg(@Body RequestBody request);

    @POST("app-staff/message/delete")
    Observable<BaseBean> deleteMsg(@Body RequestBody request);

    @GET("/wfe/flow/instance/todo/statistics")
    Observable<BaseBean> getTodoStatics();

}
