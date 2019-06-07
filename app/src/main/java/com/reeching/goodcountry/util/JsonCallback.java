package com.reeching.goodcountry.util;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.lzy.okgo.callback.AbsCallback;
import com.lzy.okgo.request.base.Request;
import com.reeching.goodcountry.BaseApplication;
import com.reeching.goodcountry.R;

import java.lang.reflect.Type;

import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by 绍轩 on 2018/1/27.
 */

public abstract class JsonCallback<T> extends AbsCallback<T> {
    private Type type;
    private Class<T> clazz;

    private JsonCallback() {
    }

    public JsonCallback(Type type) {
        this.type = type;
    }

    public JsonCallback(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public T convertResponse(Response response) throws Throwable {
        //详细自定义的原理和文档，看这里： https://github.com/jeasonlzy/okhttp-OkGo/wiki/JsonCallback
        ResponseBody body = response.body();
        if (body == null) return null;
        T data = null;
        Gson gson = new Gson();
        JsonReader jsonReader = new JsonReader(body.charStream());
        if (type != null) data = gson.fromJson(jsonReader, type);
        if (clazz != null) data = gson.fromJson(jsonReader, clazz);
        return data;
    }

    @Override
    public void onFinish() {
        super.onFinish();
    }

    @Override
    public void onStart(Request<T, ? extends Request> request) {
        super.onStart(request);
    }

    @Override
    public void onCacheSuccess(com.lzy.okgo.model.Response<T> response) {
        super.onCacheSuccess(response);
    }

    @Override
    public void onError(com.lzy.okgo.model.Response<T> response) {
        super.onError(response);
        ToastUtil.showToast(BaseApplication.getInstance(), BaseApplication.getInstance().getString(R.string.error_net));
    }
}
