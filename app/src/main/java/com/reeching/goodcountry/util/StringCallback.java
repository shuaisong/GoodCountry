package com.reeching.goodcountry.util;

import com.lzy.okgo.callback.AbsCallback;
import com.lzy.okgo.convert.StringConvert;

/**
 * Created by lenovo on 2019/3/1.
 * auther:lenovo
 * Date：2019/3/1
 */
public abstract class StringCallback extends AbsCallback<String> {
    private StringConvert convert;

    public StringCallback() {
        convert = new StringConvert();
    }

    /**
     * 拿到响应后，将数据转换成需要的格式，子线程中执行，可以是耗时操作
     *
     * @param response 需要转换的对象
     * @return 转换后的结果
     * @throws Exception 转换过程发生的异常
     */
    @Override
    public String convertResponse(okhttp3.Response response) throws Throwable {
        String s = convert.convertResponse(response);
        response.close();
        return s;
    }
}
