package com.reeching.goodcountry.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.reeching.goodcountry.Constant;
import com.reeching.goodcountry.R;
import com.reeching.goodcountry.base.BasePageFragment;
import com.reeching.goodcountry.bean.OrderBean;
import com.reeching.goodcountry.component.AppComponent;
import com.reeching.goodcountry.component.DaggerFragmentComponent;
import com.reeching.goodcountry.util.LogUtil;
import com.reeching.goodcountry.util.SpaceItemDecoration;
import com.reeching.goodcountry.view.CustomLoadMoreView;

import java.util.ArrayList;
import java.util.Iterator;

import butterknife.BindView;


public class OrderFragment extends BasePageFragment {
    private static final String TAG_TYPE = "type";
    @BindView(R.id.order_recycler)
    RecyclerView mOrderRecycler;
    @BindView(R.id.order_swip)
    SwipeRefreshLayout mRefreshLayout;

    private int type = -1;
    private ArrayList<OrderBean> mOrderBeans;
    private BaseQuickAdapter<OrderBean, BaseViewHolder> mAdapter;

    public static OrderFragment newInstance(int type) {
        OrderFragment fragment = new OrderFragment();
        Bundle args = new Bundle();
        args.putInt(TAG_TYPE, type);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            type = getArguments().getInt(TAG_TYPE);
        }
    }

    @Override
    protected void setupFragComponent(AppComponent appComponent) {
        DaggerFragmentComponent.builder().appComponent(appComponent).build().inject(this);
    }

    @Override
    protected void initData() {
        for (int i = 0; i < 4; i++) {
            OrderBean orderBean = new OrderBean();
            orderBean.setTime("xxxx-xx-xx");
            orderBean.setType(i % 2);
            ArrayList<OrderBean.GoodBean> goodBeans = new ArrayList<>();
            for (int j = 0; j < 2; j++) {
                OrderBean.GoodBean goodBean = new OrderBean.GoodBean();
                goodBean.setImg("http://dimg12.c-ctrip.com/images/20070e00000075s8u5D03_R_130_130.jpg");
                goodBean.setName("XXXXXX");
                goodBean.setNum(2);
                goodBean.setPrice(22);
                goodBeans.add(goodBean);
            }
            orderBean.setGood(goodBeans);
            mOrderBeans.add(orderBean);
        }
        if (type != -1) {
            Iterator<OrderBean> iterator = mOrderBeans.iterator();
            while (iterator.hasNext()) {
                OrderBean next = iterator.next();
                if (next.getType() != type) {
                    iterator.remove();
                }
            }
        }
        if (mRefreshLayout.isRefreshing()) {
            mRefreshLayout.setRefreshing(false);
        }

        if (mAdapter.isLoading()) {
            mAdapter.loadMoreComplete();
        }
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (type == 0 && isFirstVisible()) {
            setUserVisibleHint(true);
        }
    }

    @Override
    protected void initView() {
        mRefreshLayout.setColorSchemeResources(R.color.colorAccent, R.color.colorPrimary, R.color.green);
        mRefreshLayout.measure(0, 0);
        mRefreshLayout.setRefreshing(true);
        mOrderRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        mOrderBeans = new ArrayList<>();
        mAdapter = new BaseQuickAdapter<OrderBean, BaseViewHolder>(R.layout.order_item, mOrderBeans) {

            @Override
            protected void convert(BaseViewHolder helper, OrderBean item) {
                int sum = 0;
                for (OrderBean.GoodBean b :
                        item.getGood()) {
                    sum += b.getPrice();
                }
                helper.setText(R.id.time, item.getTime())
                        .setText(R.id.type, Constant.Order_type[item.getType()])
                        .setText(R.id.sum, "共" + item.getGood().size() + "件商品,合计：" + sum + "元");
                RecyclerView recyclerView = helper.getView(R.id.goods);
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                BaseQuickAdapter<OrderBean.GoodBean, BaseViewHolder> goodAdapter =
                        new BaseQuickAdapter<OrderBean.GoodBean, BaseViewHolder>(R.layout.good_item, item.getGood()) {
                            @Override
                            protected void convert(BaseViewHolder helper, OrderBean.GoodBean item) {
                                Glide.with(getActivity()).asBitmap().load(item.getImg())
                                        .placeholder(R.mipmap.img_shoppingmall_default_jd).error(R.mipmap.error)
                                        .into((ImageView) helper.getView(R.id.img));
                                helper.setText(R.id.name, item.getName())
                                        .setText(R.id.num, "x" + item.getNum())
                                        .setText(R.id.price, "$" + item.getPrice());
                            }
                        };
                recyclerView.addItemDecoration(new SpaceItemDecoration(1, getActivity().getResources().getColor(R.color.divider)));
                recyclerView.setAdapter(goodAdapter);
            }
        };
        View empty = LayoutInflater.from(getActivity()).inflate(R.layout.order_empty_view, null);
        TextView tv_order = empty.findViewById(R.id.tv_order);
        TextView go_shop = empty.findViewById(R.id.go_shop);
        go_shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().setResult(Constant.GOSHOP);
                getActivity().finish();
            }
        });
        tv_order.setText(String.format("暂无%s订单记录~", Constant.Order_type[type == -1 ? 3 : type]));
        mAdapter.setEmptyView(empty);
        mAdapter.setEnableLoadMore(true);
        mAdapter.setLoadMoreView(new CustomLoadMoreView());
        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                LogUtil.d("onLoadMoreRequested.....");
                mRefreshLayout.setEnabled(false);

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(4 * 1000);
                            if (getActivity() != null)
                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        mAdapter.addData(mOrderBeans);
                                        mAdapter.loadMoreComplete();
                                        mRefreshLayout.setEnabled(true);
                                    }
                                });

                        } catch (InterruptedException | NullPointerException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();


            }
        }, mOrderRecycler);
        mAdapter.disableLoadMoreIfNotFullPage(mOrderRecycler);
        mOrderRecycler.addItemDecoration(new SpaceItemDecoration(5, getActivity().getResources().getColor(R.color.divider)));
        mOrderRecycler.setAdapter(mAdapter);
        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mOrderBeans.clear();
                initData();
            }
        });
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_order;
    }

}
