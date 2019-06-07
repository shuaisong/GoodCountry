package com.reeching.goodcountry.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioGroup;

import com.reeching.goodcountry.Constant;
import com.reeching.goodcountry.R;
import com.reeching.goodcountry.base.BaseAddFragment;
import com.reeching.goodcountry.fragment.ArticleFragment;
import com.reeching.goodcountry.fragment.Home1Fragment;
import com.reeching.goodcountry.fragment.HomeFragment;
import com.reeching.goodcountry.fragment.MineFragment;
import com.reeching.goodcountry.fragment.ReleaseFragment;
import com.reeching.goodcountry.fragment.ShopFragment;
import com.reeching.goodcountry.util.FixActivityInputLeak;
import com.reeching.goodcountry.util.LogUtil;
import com.reeching.goodcountry.util.PreferenceManager;
import com.reeching.goodcountry.util.StatusBarUtil;
import com.reeching.goodcountry.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {
    private Home1Fragment mHomeFragment;
    //    private HomeFragment mHomeFragment;
    private ReleaseFragment mReleaseFragment;
    private MineFragment mMineFragment;
    private ShopFragment mShopFragment;
    private BaseAddFragment currentFG;
    private RadioGroup mRadioGroup;
    private ArticleFragment mArticleFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //当FitsSystemWindows设置 true 时，会在屏幕最上方预留出状态栏高度的 padding
        StatusBarUtil.setRootViewFitsSystemWindows(this,true);
        //设置状态栏透明
        StatusBarUtil.setTranslucentStatus(this);
        StatusBarUtil.setStatusBarDarkTheme(this, true);
        StatusBarUtil.setStatusBarColor(this, Color.WHITE);
        mRadioGroup = findViewById(R.id.navigation);
        mRadioGroup.setOnCheckedChangeListener(this);
        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        for (Fragment f :
                fragments) {
            if (f instanceof HomeFragment) {
                mHomeFragment = (Home1Fragment) f;
            }
            if (f instanceof ArticleFragment)
                mArticleFragment = (ArticleFragment) f;
            if (f instanceof ReleaseFragment)
                mReleaseFragment = (ReleaseFragment) f;
            if (f instanceof ShopFragment)
                mShopFragment = (ShopFragment) f;
            if (f instanceof MineFragment)
                mMineFragment = (MineFragment) f;
        }
        currentFG = null;
        selectFragment(mRadioGroup.getCheckedRadioButtonId());
        PreferenceManager.getInstance().putString("openId", "ohry94n9GgIBgt1AkIqhQpThLhOA");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkPermission();
        }
    }

    private final String[] allPermissions = {
            Manifest.permission.WRITE_EXTERNAL_STORAGE
            , Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION};
    private List<String> denyPermissions = new ArrayList<>(3);
    private List<String> needPermissions = new ArrayList<>(3);

    /**
     * 遍历权限
     */
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void checkPermission() {
        needPermissions.clear();
        for (String allPermission : allPermissions) {
            if (checkSelfPermission(allPermission) != PackageManager.PERMISSION_GRANTED) {
                needPermissions.add(allPermission);
            }
        }
        if (!needPermissions.isEmpty()) {
            requestPermissions(needPermissions.toArray(new String[needPermissions.size()]), 101);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 101) {
            denyPermissions.clear();
            for (int i = 0; i < grantResults.length; i++) {
                if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                    denyPermissions.add(permissions[i]);
                }
            }
            if (!denyPermissions.isEmpty()) {
                ToastUtil.showToast(this, "应用缺少权限，请给与权限以便应用正常运行");
                toSelfSetting();
            }
        }
    }

    private void toSelfSetting() {
        Intent mIntent = new Intent();
        mIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
        mIntent.setData(Uri.fromParts("package", getPackageName(), null));
        startActivity(mIntent);
        finish();
    }

    public void setRadioCheck(int idRes) {
        mRadioGroup.check(idRes);
    }

    private void selectFragment(int resId) {
        switch (resId) {
            case R.id.home:
                if (mHomeFragment == null) {
                    mHomeFragment = new Home1Fragment();
                }
                add(mHomeFragment, "home");
                break;
            case R.id.community:
                if (mArticleFragment == null) {
                    mArticleFragment = new ArticleFragment();
                }
                add(mArticleFragment, "community");
                break;
            case R.id.release:
                if (mReleaseFragment == null) {
                    mReleaseFragment = new ReleaseFragment();
                }
                add(mReleaseFragment, "release");
                break;
            case R.id.shopping:
                if (mShopFragment == null) {
                    mShopFragment = new ShopFragment();
                }
                add(mShopFragment, "shop");
                break;
            case R.id.mine:
                if (mMineFragment == null) {
                    mMineFragment = new MineFragment();
                }
                add(mMineFragment, "mine");

        }
    }

    private void add(BaseAddFragment fragment, String tag) {
        if (currentFG == fragment) {
            return;
        }
        FragmentManager mManager = getSupportFragmentManager();
        FragmentTransaction transaction = mManager.beginTransaction();
        Fragment fragmentByTag = getSupportFragmentManager().findFragmentByTag(tag);

        if (currentFG != null) {
            LogUtil.d("currentFG != null");
            if (fragment.isAdded()) {
                LogUtil.d(tag + "isAdd");
                transaction.hide(currentFG).show(fragment).commitAllowingStateLoss();
            } else {
                LogUtil.d(tag + "isNotAdd");
                transaction.hide(currentFG).add(R.id.content, fragment, tag).show(fragment).commitAllowingStateLoss();
            }
            currentFG = fragment;
        } else {
            LogUtil.d("currentFG == null");
            if (fragmentByTag == null) {
                LogUtil.d("fragmentByTag == null");
                transaction.add(R.id.content, fragment, tag).show(fragment).commitAllowingStateLoss();
                currentFG = fragment;
            } else {
                LogUtil.d("fragmentByTag != null");
                currentFG = (BaseAddFragment) fragmentByTag;
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        FixActivityInputLeak.fixInputMethodManagerLeak(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        selectFragment(checkedId);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        LogUtil.d(resultCode);
        if (resultCode == Constant.GOSHOP) {
            mRadioGroup.check(R.id.shopping);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

}
