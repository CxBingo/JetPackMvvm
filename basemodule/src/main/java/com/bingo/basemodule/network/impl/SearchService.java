package com.bingo.basemodule.network.impl;

import com.bingo.basemodule.network.bean.BaseBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @author francisbingo on 1/8/21 4:37 PM
 * <p>
 * 查询模块
 */

public interface SearchService {
    // 客户查询-姓名
    @GET("app-staff/gmanager/user/list")
    Observable<BaseBean> searchNameInfo(@Query("name") String name);

    // 客户查询-房号
    @GET("app-staff/gmanager/user/list")
    Observable<BaseBean> searchRoomInfo(@Query("room_name") String roomName);

    // 客户查询-联系电话
    @GET("app-staff/gmanager/user/list")
    Observable<BaseBean> searchMobileInfo(@Query("mobile") String mobile);

    // 车位查询
    @GET("app-staff/gmanager/parking/list")
    Observable<BaseBean> searchParkingInfo(@Query("user_name") String user_name, @Query("name") String name);

    //房屋查询
    @GET("app-staff/gmanager/room/list")
    Observable<BaseBean> searchRoomInfo(@Query("name") String name, @Query("room_name") String room_name, @Query("mobile") String mobile);

    //车辆查询
    @GET("app-staff/gmanager/vehicle/list")
    Observable<BaseBean> searchVehicleInfo(@Query("name") String name, @Query("plate_number") String plate_number, @Query("mobile") String mobile);

    // 客户查询-详情
    @GET("app-staff/gmanager/user/get")
    Observable<BaseBean> searchUserInfo(@Query("id") int userId);

    // 车位-详情
    @GET("app-staff/gmanager/parking/get")
    Observable<BaseBean> getParkingInfo(@Query("id") int userId);

    // 车辆-详情
    @GET("app-staff/gmanager/vehicle/get")
    Observable<BaseBean> getVehicleInfo(@Query("id") int userId);

    // 房屋详情
    @GET("app-staff/estate/room/get")
    Observable<BaseBean> searchRoomDetails(@Query("id") int roomID);

    // 房屋详情 入住人员
    @GET("app-staff/estate/room/user")
    Observable<BaseBean> searchRoomCheckInUser(@Query("id") int roomID);


}
