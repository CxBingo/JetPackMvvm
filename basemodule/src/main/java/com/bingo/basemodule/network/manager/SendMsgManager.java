package com.bingo.basemodule.network.manager;

import android.os.Build;
import android.text.TextUtils;

import com.bingo.basemodule.network.BingoConstant;
import com.bingo.basemodule.network.bean.BaseBean;
import com.bingo.basemodule.network.impl.RetrofitService;
import com.bingo.basemodule.network.net.HttpChannel;

import org.json.JSONException;
import org.json.JSONObject;

import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by francisbingo on 2020/10/26 14:32
 * <p>
 * 网络请求
 */

public class SendMsgManager {
    private static SendMsgManager manager;
    private HttpChannel httpChannel;
    private RetrofitService retrofitService;

    public static SendMsgManager getInstance() {
        if (manager != null) {
            manager = null;
        }
        return manager = new SendMsgManager();
    }

    public static SendMsgManager getInstance(String url) {
        if (manager != null) {
            manager = null;
        }
        return manager = new SendMsgManager(url);
    }

    private SendMsgManager() {
        httpChannel = HttpChannel.getInstance();
        retrofitService = httpChannel.getRetrofitService();
    }

    // 腾讯获取Token用于人脸图片的上传
    private SendMsgManager(String url) {
        httpChannel = HttpChannel.getInstance(url);
        retrofitService = httpChannel.getRetrofitService();
    }

    // 设置公共参数
    private void setCommonJson(JSONObject commonJson) {
        try {
            commonJson.put("from_role", BingoConstant.FROM_ROLE);
            commonJson.put("device_type", BingoConstant.DEVICE_TYPE);
            commonJson.put("channel", BingoConstant.CHANNEL);
            commonJson.put("sdk_version", BingoConstant.SDK_VERSION);
            commonJson.put("app_version", BingoConstant.SDK_VERSION);
            commonJson.put("os_version", Build.VERSION.RELEASE);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


//    // 获取通话记录
//    public void getCallRecord(int lastRecord) {
//        JSONObject jsonObject = new JSONObject();
//        try {
//            jsonObject.put("last_record_id", lastRecord);
//            setCommonJson(jsonObject);
//            jsonObject.put("cmd", BingoConstant.GmCmd.GET_CALL_RECORD);
//            SignUtil.signParam(jsonObject);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), jsonObject.toString());
//        Observable<BaseArrayBean<CallRecordResponse>> observable = retrofitService.commonIntercomPost(GMUserConfig.get().getToken(), requestBody);
//        httpChannel.sendArrayMessage(observable, BingoConstant.GmCmd.GET_CALL_RECORD);
//    }


    /**
     * 获取验证码
     *
     * @param jsonObject
     */
    public void getVerifyCode(JSONObject jsonObject) {
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), jsonObject.toString());
        Observable<BaseBean> verifyCode = httpChannel.getLoginService().getVerifyCode(requestBody);
        httpChannel.sendMessage(verifyCode, BingoConstant.GmCmd.GET_VERIFY_CODE);
    }


    /**
     * 获取验证码 忘记密码
     *
     * @param jsonObject
     */
    public void forgetPwdVerifyCode(JSONObject jsonObject) {
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), jsonObject.toString());
        Observable<BaseBean> verifyCode = httpChannel.getLoginService().forgetPwdVerifyCode(requestBody);
        httpChannel.sendMessage(verifyCode, BingoConstant.GmCmd.GET_FORGET_VERIFY_CODE);
    }


    public void resetPwd(JSONObject jsonObject) {
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), jsonObject.toString());
        Observable<BaseBean> verifyCode = httpChannel.getLoginService().resetPwd(requestBody);
        httpChannel.sendMessage(verifyCode, BingoConstant.GmCmd.RESET_PWD);
    }

    public void checkForgetCode(JSONObject jsonObject) {
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), jsonObject.toString());
        Observable<BaseBean> verifyCode = httpChannel.getLoginService().resetPwd(requestBody);
        httpChannel.sendMessage(verifyCode, BingoConstant.GmCmd.CHECK_FORGET_CODE);
    }

    /**
     * 验证码登陆
     *
     * @param jsonObject
     */
    public void loginByVerifyCode(JSONObject jsonObject) {
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), jsonObject.toString());
        Observable<BaseBean> verifyCode = httpChannel.getLoginService().loginByVerifyCode(requestBody);
        httpChannel.sendMessage(verifyCode, BingoConstant.GmCmd.LOGIN_VERIFY_CODE);
    }

    /**
     * 获取公司列表
     *
     * @param jsonObject
     */
    public void getCompanyList(JSONObject jsonObject) {
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), jsonObject.toString());
        Observable<BaseBean> verifyCode = httpChannel.getLoginService().getCompanyList(requestBody);
        httpChannel.sendMessage(verifyCode, BingoConstant.GmCmd.GET_COMPANY_LIST);
    }


    /**
     * 当月排班计划
     *
     * @param jsonObject
     */
    public void getCheckPlan(JSONObject jsonObject) {
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), jsonObject.toString());
        Observable<BaseBean> verifyCode = retrofitService.getCheckPlan(requestBody);
        httpChannel.sendMessage(verifyCode, BingoConstant.GmCmd.GET_CHECK_PLAN);
    }

    /**
     * 考勤统计
     *
     * @param jsonObject
     */
    public void getScheduleReport(JSONObject jsonObject) {
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), jsonObject.toString());
        Observable<BaseBean> verifyCode = retrofitService.getScheduleReport(requestBody);
        httpChannel.sendMessage(verifyCode, BingoConstant.GmCmd.GET_SCHEDULE_REPORT);
    }


    // 一键登录
    public void loginByOneKey(JSONObject jsonObject) {
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), jsonObject.toString());
        Observable<BaseBean> verifyCode = httpChannel.getLoginService().loginByOneKey(requestBody);
        httpChannel.sendMessage(verifyCode, BingoConstant.GmCmd.GET_ONE_KEY_LOGIN);
    }

    // 密码登录
    public void loginByAccount(JSONObject jsonObject) {
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), jsonObject.toString());
        Observable<BaseBean> verifyCode = httpChannel.getLoginService().accountLogin(requestBody);
        httpChannel.sendMessage(verifyCode, BingoConstant.GmCmd.GET_ACCOUNT_LOGIN);
    }

    // 获取位置信息
    public void getLocationInfo(int groupid, JSONObject jsonObject) {
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), jsonObject.toString());
        Observable<BaseBean> verifyCode = httpChannel.getRetrofitService().getLocationInfo(requestBody);
        httpChannel.sendMessage(verifyCode, BingoConstant.GmCmd.GET_LOCATION_INFO);
    }

    public void getEmplaoyee(int companyId) {
        Observable<BaseBean> verifyCode = httpChannel.getRetrofitService().getEmployeeHouse(companyId);
        httpChannel.sendMessage(verifyCode, BingoConstant.GmCmd.GET_EMPLOYEE_INFO);
    }

    public void getTodoStatics() {
        Observable<BaseBean> verifyCode = httpChannel.getRetrofitService().getTodoStatics();
        httpChannel.sendMessage(verifyCode, BingoConstant.GmCmd.GET_TO_DO_STATICS);
    }

    public void getDayWork(JSONObject jsonObject) {
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), jsonObject.toString());
        Observable<BaseBean> verifyCode = httpChannel.getRetrofitService().getDayWorkInfo(requestBody);
        httpChannel.sendMessage(verifyCode, BingoConstant.GmCmd.GET_DAYWORK_INFO);

    }

    //打卡
    public void clockCard(JSONObject jsonObject) {
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), jsonObject.toString());
        Observable<BaseBean> verifyCode = httpChannel.getRetrofitService().getClockCard(requestBody);
        httpChannel.sendMessage(verifyCode, BingoConstant.GmCmd.CLOCK_CARD);

    }

    // 客户姓名搜索
    public void searchNameResult(String name) {
        Observable<BaseBean> verifyCode = httpChannel.getSearchService().searchNameInfo(name);
        httpChannel.sendMessage(verifyCode, BingoConstant.GmCmd.USER_SEARCH_NAME);
    }

    // 客户-房号搜索
    public void searchRoomResult(String searchStr) {
        Observable<BaseBean> verifyCode = httpChannel.getSearchService().searchRoomInfo(searchStr);
        httpChannel.sendMessage(verifyCode, BingoConstant.GmCmd.USER_SEARCH_NAME);

    }

    // 客户-手机搜索
    public void searchMobileResult(String searchStr) {
        Observable<BaseBean> verifyCode = httpChannel.getSearchService().searchMobileInfo(searchStr);
        httpChannel.sendMessage(verifyCode, BingoConstant.GmCmd.USER_SEARCH_MOBILE);

    }

    // 客户-详情
    public void searchUserDetailResult(int searchId) {
        Observable<BaseBean> verifyCode = httpChannel.getSearchService().searchUserInfo(searchId);
        httpChannel.sendMessage(verifyCode, BingoConstant.GmCmd.USER_SEARCH_DETAIL);

    }

    //车位 搜索
    public void searchParkingInfo(String user_name, String name) {
        Observable<BaseBean> searchParkingInfo = httpChannel.getSearchService().searchParkingInfo(user_name, name);
        httpChannel.sendMessage(searchParkingInfo, !TextUtils.isEmpty(user_name) ? BingoConstant.GmCmd.SEARCH_PARK_BY_USER : BingoConstant.GmCmd.SEARCH_PARK_BY_NAME);
    }

    //车位 详情
    public void getParkingInfo(int userid) {
        Observable<BaseBean> getParkingInfo = httpChannel.getSearchService().getParkingInfo(userid);
        httpChannel.sendMessage(getParkingInfo, BingoConstant.GmCmd.GET_PARK_BY_ID);
    }

    //房屋 搜索
    public void searchRoomInfo(String name, String room_name, String mobile) {
        Observable<BaseBean> searchParkingInfo = httpChannel.getSearchService().searchRoomInfo(name, room_name, mobile);
        String cmd = null;
        if (!TextUtils.isEmpty(name)) {
            cmd = BingoConstant.GmCmd.SEARCH_ROOM_BY_NAME;
        } else if (!TextUtils.isEmpty(room_name)) {
            cmd = BingoConstant.GmCmd.SEARCH_ROOM_BY_ROOM;
        } else if (!TextUtils.isEmpty(mobile)) {
            cmd = BingoConstant.GmCmd.SEARCH_ROOM_BY_MOBILE;
        }
        httpChannel.sendMessage(searchParkingInfo, cmd);
    }

    //车辆 搜索
    public void searchVehicleInfo(String name, String plate_number, String mobile) {
        Observable<BaseBean> searchParkingInfo = httpChannel.getSearchService().searchVehicleInfo(name, plate_number, mobile);
        String cmd = null;
        if (!TextUtils.isEmpty(name)) {
            cmd = BingoConstant.GmCmd.SEARCH_VEHICLE_BY_NAME;
        } else if (!TextUtils.isEmpty(plate_number)) {
            cmd = BingoConstant.GmCmd.SEARCH_VEHICLE_BY_PLATE;
        } else if (!TextUtils.isEmpty(mobile)) {
            cmd = BingoConstant.GmCmd.SEARCH_VEHICLE_BY_MOBILE;
        }
        httpChannel.sendMessage(searchParkingInfo, cmd);
    }


    // 房屋-详情
    public void searchRoomDetails(int searchId) {
        Observable<BaseBean> verifyCode = httpChannel.getSearchService().searchRoomDetails(searchId);
        httpChannel.sendMessage(verifyCode, BingoConstant.GmCmd.SEARCH_ROOM_DETAILS);


        Observable<BaseBean> checkInUser = httpChannel.getSearchService().searchRoomCheckInUser(searchId);
        httpChannel.sendMessage(checkInUser, BingoConstant.GmCmd.SEARCH_ROOM_CHECK_IN_USER);


    }

    /**
     * 获取配置列表
     */
    public void getConfigs() {
        Observable<BaseBean> verifyCode = httpChannel.getRetrofitService().getConfig();
        httpChannel.sendMessage(verifyCode, BingoConstant.GmCmd.GET_CONFIG_LIST);
    }

    //车辆详情
    public void getVehicleDetail(int detailId) {
        Observable<BaseBean> getParkingInfo = httpChannel.getSearchService().getVehicleInfo(detailId);
        httpChannel.sendMessage(getParkingInfo, BingoConstant.GmCmd.GET_VEHICLE_BY_ID);

    }

    // 获取用户信息
    public void getUserInfo() {
        Observable<BaseBean> getUserInfo = httpChannel.getLoginService().getUserInfo();
        httpChannel.sendMessage(getUserInfo, BingoConstant.GmCmd.GET_USER_INFO);
    }

    //工单消息列表
    public void getOrderMsg(JSONObject jsonObject) {
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), jsonObject.toString());
        Observable<BaseBean> getMsg = httpChannel.getRetrofitService().getOrderMsg(requestBody);
        httpChannel.sendMessage(getMsg, BingoConstant.GmCmd.MESSAGE_ORDER_LIST_DATA);
    }

    //公告消息列表
    public void getNotiMsg(JSONObject jsonObject) {
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), jsonObject.toString());
        Observable<BaseBean> getMsg = httpChannel.getRetrofitService().getOrderMsg(requestBody);
        httpChannel.sendMessage(getMsg, BingoConstant.GmCmd.MESSAGE_NOTIFICATION_LIST_DATA);
    }

    //消息详情
    public void getMsgDetail(int msgid) {
        Observable<BaseBean> getMsg = httpChannel.getRetrofitService().getMsgDetail(msgid);
        httpChannel.sendMessage(getMsg, BingoConstant.GmCmd.MESSAGE_DETAIL_DATA);
    }

    // 阅读消息
    public void readMsg(JSONObject jsonObject) {
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), jsonObject.toString());
        Observable<BaseBean> readMsg = httpChannel.getRetrofitService().readMsg(requestBody);
        httpChannel.sendMessage(readMsg, BingoConstant.GmCmd.MESSAGE_READ_DATA);
    }

    public void deleteMsg(JSONObject jsonObject) {
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), jsonObject.toString());
        Observable<BaseBean> readMsg = httpChannel.getRetrofitService().deleteMsg(requestBody);
        httpChannel.sendMessage(readMsg, BingoConstant.GmCmd.MESSAGE_DELETE_DATA);
    }

    public void exitApp() {
        Observable<BaseBean> getMsg = httpChannel.getLoginService().exitApp();
        httpChannel.sendMessage(getMsg, BingoConstant.GmCmd.EXIT_APP);
    }
}
