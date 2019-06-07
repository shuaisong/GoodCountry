package com.reeching.goodcountry.util;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * Created by lenovo on 2019/3/13.
 * auther:lenovo
 * Date：2019/3/13
 */
public class LocationUtil {
    public static void getLocation(TextView textView) {
        Criteria c = new Criteria();
        c.setPowerRequirement(Criteria.POWER_LOW);//设置耗电量为低耗电
        c.setBearingAccuracy(Criteria.ACCURACY_COARSE);//设置精度标准为粗糙
        c.setAltitudeRequired(false);//设置海拔不需要
        c.setBearingRequired(false);//设置导向不需要
        c.setAccuracy(Criteria.ACCURACY_LOW);//设置精度为低
        c.setCostAllowed(false);//设置成本为不需要
        //... Criteria 还有其他属性
        LocationManager manager = (LocationManager) textView.getContext().getSystemService(Context.LOCATION_SERVICE);
        String bestProvider = manager.getBestProvider(c, true);
        //得到定位信息
        if (ActivityCompat.checkSelfPermission(textView.getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(textView.getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        Location location = null;
        if (!TextUtils.isEmpty(bestProvider)) {
            location = manager.getLastKnownLocation(bestProvider);
        }
        if (null == location) {
            //如果没有最好的定位方案则手动配置
            if (manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                location = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            } else if (manager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
                location = manager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            } else if (manager.isProviderEnabled(LocationManager.PASSIVE_PROVIDER)) {
                location = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            }
        }
        if (null == location) {
            LogUtil.i("获取定位失败!");
            return;
        }
        //通过地理编码的到具体位置信息
        Geocoder geocoder = new Geocoder(textView.getContext(), Locale.CHINESE);
        List<Address> addresses;
        try {
            addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
            if (addresses.size() <= 0) {
                LogUtil.i("获取定位失败!");
            }
            Address address = addresses.get(0);
            String locality = address.getLocality();//得到城市
            LogUtil.d(locality);
            textView.setText(locality);
        } catch (IOException | IndexOutOfBoundsException e) {
            LogUtil.i("获取定位失败!");
            e.printStackTrace();
        }

    }
}
