package com.bingo.basemodule.network.net;

import android.util.Log;

import androidx.annotation.NonNull;

import com.bingo.basemodule.network.BingoConstant;
import com.bingo.basemodule.network.bean.BaseArrayBean;
import com.bingo.basemodule.network.bean.BaseBean;
import com.bingo.basemodule.network.impl.LoginService;
import com.bingo.basemodule.network.impl.RetrofitService;
import com.bingo.basemodule.network.impl.SearchService;
import com.bingo.basemodule.network.manager.ReceiveMsgManager;
import com.bingo.basemodule.network.utils.RetrofitUtil;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by francisbingo on 2020/10/26 14:33
 */

public class HttpChannel {
    private static HttpChannel httpChannel;
    private RetrofitService retrofitService;
    private LoginService loginService;
    private SearchService searchService;

    public static HttpChannel getInstance() {
        return httpChannel = new HttpChannel();
    }

    public static HttpChannel getInstance(String url) {
        return httpChannel = new HttpChannel(url);
    }

    private HttpChannel() {
        // 初始化Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BingoConstant.SERVER_DEV_URL)
                .addConverterFactory(GsonConverterFactory.create()) // json解析
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // 支持RxJava
                .client(RetrofitUtil.getOkHttpClient()) // 打印请求参数
                .build();
        retrofitService = retrofit.create(RetrofitService.class);
        loginService = retrofit.create(LoginService.class);
        searchService = retrofit.create(SearchService.class);
    }

    private HttpChannel(String url) {
        // 初始化Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create()) // json解析
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // 支持RxJava
                .client(RetrofitUtil.getOkHttpClient()) // 打印请求参数
                .build();
        retrofitService = retrofit.create(RetrofitService.class);
        loginService = retrofit.create(LoginService.class);
        searchService = retrofit.create(SearchService.class);
    }

    /**
     * 发送消息
     *
     * @param observable Observable<? extends BaseBean>
     * @param cmdStr     请求类型
     */
    public void sendMessage(Observable<? extends BaseBean> observable, final String cmdStr) {
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull BaseBean baseBean) {
                        Log.i("http返回：", baseBean.toString() + "");
                        ReceiveMsgManager.getInstance().dispatchMessage(baseBean, cmdStr);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e("httperror", e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * 发送数组消息(服务端接口没有统一导致需要客户端兼容)
     *
     * @param observable Observable<? extends BaseBean>
     * @param cmdStr     请求类型
     */
    public void sendArrayMessage(Observable<? extends BaseArrayBean> observable, final String cmdStr) {
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseArrayBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull BaseArrayBean baseBean) {
                        Log.i("http返回：", baseBean.toString() + "");
                        ReceiveMsgManager.getInstance().dispatchMessage(baseBean, cmdStr);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e("httperror", e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    public RetrofitService getRetrofitService() {
        return retrofitService;
    }

    public LoginService getLoginService() {
        return loginService;
    }

    public SearchService getSearchService() {
        return searchService;
    }
}
