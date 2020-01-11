package com.bw.forwardweek3.view.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.bw.forwardweek3.R;
import com.bw.forwardweek3.base.BaseActivity;
import com.bw.forwardweek3.contract.ICartContract;
import com.bw.forwardweek3.model.bean.CartBean;
import com.bw.forwardweek3.presenter.CartPresenter;
import com.bw.forwardweek3.view.adapter.CartAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

//购物车
public class CartActivity extends BaseActivity<CartPresenter> implements ICartContract.IView {


    @BindView(R.id.img)
    ImageView mImg;
    @BindView(R.id.lv)
    ExpandableListView mLv;
    @BindView(R.id.cb_cart_all_select)
    CheckBox mCbCartAllSelect;
    @BindView(R.id.tv_cart_total_price)
    TextView mTvCartTotalPrice;
    @BindView(R.id.btn_cart_pay)
    Button mBtnCartPay;
    @BindView(R.id.smart_refresh)
    SmartRefreshLayout smartRefreshLayout;

    private int page = 1;
    private CartAdapter cartAdapter;


    //商家集合 旧数据
    List<CartBean.ResultBean> list = new ArrayList<>();

    @Override
    protected void initData() {
        // TODO: 2020/1/11 获取传递过来的，用户头像
        String key = getIntent().getStringExtra("key");
        Glide.with(this).load(key)
                //错误
                .error(R.mipmap.ic_launcher)
                //占位图
                .placeholder(R.mipmap.ic_launcher_round)
                //圆角
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(30)))
                .into(mImg);


        // TODO: 2020/1/11 请求购物车的数据
        mPresenter.getCartData(page);
    }

    @Override
    protected void initView() {
        // TODO: 2020/1/11 支持上下拉
        smartRefreshLayout.setEnableRefresh(true);
        smartRefreshLayout.setEnableLoadMore(true);

        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                // TODO: 2020/1/11 清除旧数据
                list.clear();
                page =1;
                mPresenter.getCartData(page);
                // TODO: 2020/1/11 隐藏加载进度
                smartRefreshLayout.finishRefresh();
            }
        });
        smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                mPresenter.getCartData(page);
                // TODO: 2020/1/11 隐藏加载进度
                smartRefreshLayout.finishLoadMore();
            }
        });
    }

    @Override
    protected CartPresenter providePresenter() {
        return new CartPresenter();
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_cart;
    }

    @Override
    public void onCartSuccess(CartBean cartBean) {
        //商家集合
        List<CartBean.ResultBean> newList = cartBean.getResult();
        list.addAll(newList);
        cartAdapter = new CartAdapter(list);
        cartAdapter.setOnCartClickListener(new CartAdapter.OnCartClickListener() {
            @Override
            public void OnCartClick() {
                // TODO: 2020/1/11 计算价格，计算是否全选
                float totalPrice = cartAdapter.calculateTotalPrice();
                boolean isAllChecked = cartAdapter.calculateIsAllChecked();
                mTvCartTotalPrice.setText("总价￥" + totalPrice);
                mCbCartAllSelect.setChecked(isAllChecked);
            }
        });


        // TODO: 2020/1/11  给二级列表设置适配器
        mLv.setAdapter(cartAdapter);

        // TODO: 2020/1/11 展开二级列表
        for (int i = 0; i < list.size(); i++) {
            mLv.expandGroup(i);
        }

    }

    @Override
    public void onCartFailure(Throwable throwable) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.cb_cart_all_select)
    public void onViewClicked() {
        // TODO: 2020/1/11 拿到旧状态
        if (cartAdapter != null) {
            boolean isAllChecked = cartAdapter.calculateIsAllChecked();
            isAllChecked = !isAllChecked;
            cartAdapter.checkAll(isAllChecked);

            // TODO: 2020/1/11 全选之后，重新计算价格和选中状态
            float totalPrice = cartAdapter.calculateTotalPrice();
            mTvCartTotalPrice.setText("总价￥" + totalPrice);
        }
    }
}
