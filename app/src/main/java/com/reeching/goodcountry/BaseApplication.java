package com.reeching.goodcountry;

import android.app.Application;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.interceptor.HttpLoggingInterceptor;
import com.previewlibrary.ZoomMediaLoader;
import com.reeching.goodcountry.component.AppComponent;
import com.reeching.goodcountry.component.DaggerAppComponent;
import com.reeching.goodcountry.module.AppModule;
import com.reeching.goodcountry.util.ImageLoader;
import com.reeching.goodcountry.util.PreferenceManager;
import com.squareup.leakcanary.LeakCanary;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import okhttp3.OkHttpClient;

/**
 * Created by lenovo on 2019/1/23.
 * auther:lenovo
 * Date：2019/1/23
 */
public class BaseApplication extends Application {
    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        LeakCanary.install(this);
        initCompoent();
        PreferenceManager.init(this);
        mApplication = this;
        ZoomMediaLoader.getInstance().init(new ImageLoader());
        initOKGO();
    }

    private void initOKGO() {

        OkHttpClient.Builder mBuilder = new OkHttpClient.Builder();
        //log相关
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor("OkGo");
        loggingInterceptor.setPrintLevel(HttpLoggingInterceptor.Level.BASIC);        //log打印级别，决定了log显示的详细程度
        loggingInterceptor.setColorLevel(Level.INFO);                               //log颜色级别，决定了log在控制台显示的颜色
        mBuilder.addInterceptor(loggingInterceptor);                                 //添加OkGo默认debug日志
        //超时时间设置，默认60秒
        mBuilder.readTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);      //全局的读取超时时间
        mBuilder.writeTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);     //全局的写入超时时间
        mBuilder.connectTimeout(20 * 1000, TimeUnit.MILLISECONDS);   //全局的连接超时时间
        OkGo.getInstance().init(this)
                .setOkHttpClient(mBuilder.build())
                .setRetryCount(3)
                .setCacheMode(CacheMode.DEFAULT)
                .setCacheTime(100 * 60 * 60 * 24);
    }

    private void initCompoent() {
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    private static BaseApplication mApplication;

    public static BaseApplication getInstance() {
        return mApplication;
    }
}
