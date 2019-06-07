package com.reeching.goodcountry.fragment;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.reeching.goodcountry.Constant;
import com.reeching.goodcountry.Contact.MineContact;
import com.reeching.goodcountry.R;
import com.reeching.goodcountry.activity.ArticleManageActivity;
import com.reeching.goodcountry.activity.AuthenticationTypeActivity;
import com.reeching.goodcountry.activity.CardActivity;
import com.reeching.goodcountry.activity.CaseManageActivity;
import com.reeching.goodcountry.activity.CouponManageActivity;
import com.reeching.goodcountry.activity.FriendActivity;
import com.reeching.goodcountry.activity.OrderActivity;
import com.reeching.goodcountry.activity.WalletActivity;
import com.reeching.goodcountry.base.BaseAddFragment;
import com.reeching.goodcountry.bean.MineNumBean;
import com.reeching.goodcountry.component.AppComponent;
import com.reeching.goodcountry.component.DaggerFragmentComponent;
import com.reeching.goodcountry.util.LogUtil;
import com.reeching.goodcountry.util.PreferenceManager;
import com.reeching.goodcountry.view.MineItem;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by lenovo on 2019/1/23.
 * auther:lenovo
 * Date：2019/1/23
 */
public class MineFragment extends BaseAddFragment implements View.OnClickListener, MineContact.View {

    @BindView(R.id.head)
    ImageView mHead;
    @BindView(R.id.name)
    TextView mName;
    @BindView(R.id.my_card)
    TextView mMyCard;
    @BindView(R.id.flag)
    TextView mFlag;
    @BindView(R.id.right_head)
    ImageView mRightHead;
    @BindView(R.id.comment)
    TextView mComment;
    @BindView(R.id.fabulous)
    TextView mFabulous;
    @BindView(R.id.collection)
    TextView mCollection;
    @BindView(R.id.integral)
    TextView mIntegral;
    @BindView(R.id.line1)
    LinearLayout mLine1;
    @BindView(R.id.view1)
    View mView1;
    @BindView(R.id.balance)
    TextView mBalance;
    @BindView(R.id.money)
    TextView mMoney;
    @BindView(R.id.view2)
    View mView2;
    @BindView(R.id.case_manage)
    MineItem mCaseManage;
    @BindView(R.id.article_manage)
    MineItem mArticleManage;
    @BindView(R.id.order_manage)
    MineItem mOrderManage;
    @BindView(R.id.coupon_manage)
    MineItem mCouponManage;
    @BindView(R.id.invication_manage)
    MineItem mInvicationManage;
    @BindView(R.id.parent)
    ConstraintLayout mParent;

    @Override
    protected void setupFragComponent(AppComponent appComponent) {
        DaggerFragmentComponent.builder().appComponent(appComponent).build().inject(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        RequestOptions options = new RequestOptions().circleCrop();
        Glide.with(getActivity()).asBitmap().placeholder(R.mipmap.img_my_default_head)
                .load(Constant.head_path).apply(options).into(mHead);
        final Drawable mDrawable_right = getResources().getDrawable(R.mipmap.arrow_home_right);
        mMyCard.setCompoundDrawablePadding(10);
        mMyCard.setCompoundDrawablesWithIntrinsicBounds(null, null, mDrawable_right, null);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_mine;
    }


    @OnClick({R.id.flag, R.id.case_manage, R.id.article_manage,
            R.id.order_manage, R.id.coupon_manage,
            R.id.my_card, R.id.line_balance, R.id.invication_manage})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.flag:
                startActivity(new Intent(getActivity(), AuthenticationTypeActivity.class));
                break;
            case R.id.case_manage:
                startActivity(new Intent(getActivity(), CaseManageActivity.class));
                break;
            case R.id.article_manage:
                Intent intent = new Intent(getActivity(), ArticleManageActivity.class);
                intent.putExtra("openId", PreferenceManager.getInstance().getString("openId"));
                startActivity(intent);
                break;
            case R.id.order_manage:
                getActivity().startActivityForResult(new Intent(getActivity(), OrderActivity.class), Constant.GOSHOP);
                break;
            case R.id.coupon_manage:
                getActivity().startActivityForResult(new Intent(getActivity(), CouponManageActivity.class), Constant.GOSHOP);
                break;
            case R.id.my_card:
                getActivity().startActivityForResult(new Intent(getActivity(), CardActivity.class), Constant.GOSHOP);
                break;
            case R.id.line_balance:
                getActivity().startActivity(new Intent(getActivity(), WalletActivity.class));
                break;
            case R.id.invication_manage:
                getActivity().startActivity(new Intent(getActivity(), FriendActivity.class));
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        LogUtil.d(resultCode);
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onClick(View v) {
        LogUtil.d(v.getId());
        Intent intent;
        switch (v.getId()) {
           /* case R.id.doctor:
                intent = new Intent(getActivity(), AuthenticationDoctorActivity.class);
                startActivityForResult(intent, Constant.AUTHENTICATION_DOCTOR);
                mPopupWindow.dismiss();
                break;
            case R.id.patient:
                intent = new Intent(getActivity(), AuthenticationPatientActivity.class);
                startActivityForResult(intent, Constant.AUTHENTICATION_PATIENT);
                mPopupWindow.dismiss();
                break;*/
        }
    }

    @Override
    public void showNum(MineNumBean numBean) {
        mOrderManage.setDescription(String.valueOf(numBean.getOrderSize()));
        mCaseManage.setDescription(String.valueOf(numBean.getCaseSize()));
    }

    @Override
    public void start() {

    }

    @Override
    public void compete() {

    }
}
