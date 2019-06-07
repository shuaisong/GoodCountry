package com.reeching.goodcountry.fragment;


import android.content.Intent;
import android.view.View;

import com.reeching.goodcountry.R;
import com.reeching.goodcountry.activity.ArticleManageActivity;
import com.reeching.goodcountry.activity.ReleaseArticleActivity;
import com.reeching.goodcountry.base.BaseAddFragment;
import com.reeching.goodcountry.base.BasePageFragment;
import com.reeching.goodcountry.component.AppComponent;
import com.reeching.goodcountry.component.DaggerFragmentComponent;

import butterknife.OnClick;

public class ReleaseFragment extends BaseAddFragment {


    @Override
    protected void setupFragComponent(AppComponent appComponent) {
        DaggerFragmentComponent.builder().appComponent(appComponent).build().inject(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_release;
    }


    @OnClick({R.id.release_article, R.id.release_issue, R.id.share_article})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.release_article:
                intent = new Intent(getActivity(), ReleaseArticleActivity.class);
                intent.putExtra("type", 1);
                startActivity(intent);
                break;
            case R.id.release_issue:
                intent = new Intent(getActivity(), ReleaseArticleActivity.class);
                intent.putExtra("type", 2);
                startActivity(intent);
                break;
            case R.id.share_article:
                intent = new Intent(getActivity(), ArticleManageActivity.class);
                intent.putExtra("share", true);
                startActivity(intent);
                break;
        }
    }
}
