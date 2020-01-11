package com.bw.forwardweek3.view.adapter;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.bw.forwardweek3.R;
import com.bw.forwardweek3.model.bean.CartBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CartAdapter extends BaseExpandableListAdapter {
    private List<CartBean.ResultBean> sellerList;

    public CartAdapter(List<CartBean.ResultBean> sellerList) {

        this.sellerList = sellerList;
    }

    @Override
    public int getGroupCount() {
        return sellerList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return sellerList.get(groupPosition).getShoppingCartList().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return null;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupViewHolder groupViewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart_parent, parent, false);
            // TODO: 2020/1/11 必须先给   convertView 赋值之后，再 new holder
            groupViewHolder = new GroupViewHolder(convertView);
            convertView.setTag(groupViewHolder);
        } else {
            groupViewHolder = (GroupViewHolder) convertView.getTag();
        }

        //拿到商家bean
        CartBean.ResultBean seller = sellerList.get(groupPosition);

        //给商家名字赋值
        groupViewHolder.mSellerNameTv.setText(seller.getCategoryName());

        //当前商家下的所有商品
        List<CartBean.ResultBean.ShoppingCartListBean> shoppingCartList = seller.getShoppingCartList();

        // TODO:1、假设，商家的状态是 true
        boolean isAllChecked = true;
        // TODO:2、遍历商家下的所有商品
        for (int i = 0; i < shoppingCartList.size(); i++) {
            CartBean.ResultBean.ShoppingCartListBean shoppingCartListBean = shoppingCartList.get(i);
            // TODO: 3、只要有一个未选中，商家就应该是未选中的 false 状态
            if (shoppingCartListBean.isChecked() == false) {
                isAllChecked = false;
                break;
            }
        }
        // TODO: 4、给商家的checkbox设定状态
        groupViewHolder.mSellerCb.setChecked(isAllChecked);
        boolean finalIsAllChecked = isAllChecked;
        groupViewHolder.mSellerCb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 1、拿到商家点击前的状态 置反
                boolean old = finalIsAllChecked;
                old = !old;
                // TODO: 2、遍历所有商品，修改所有商品bean的状态
                for (int i = 0; i < shoppingCartList.size(); i++) {
                    CartBean.ResultBean.ShoppingCartListBean shoppingCartListBean = shoppingCartList.get(i);
                    shoppingCartListBean.setChecked(old);
                }
                // TODO: 3、 刷新适配器
                notifyDataSetChanged();

                // TODO: 2020/1/11 通知价格改变
                if (onCartClickListener != null) {
                    onCartClickListener.OnCartClick();
                }
            }
        });

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildViewHolder childViewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart_child, parent, false);
            // TODO: 2020/1/11 必须先给   convertView 赋值之后，再 new holder
            childViewHolder = new ChildViewHolder(convertView);
            convertView.setTag(childViewHolder);
        } else {
            childViewHolder = (ChildViewHolder) convertView.getTag();
        }

        //商品bean
        CartBean.ResultBean.ShoppingCartListBean shoppingCartListBean = sellerList.get(groupPosition).getShoppingCartList().get(childPosition);

        //绑定数据
        childViewHolder.mProductTitleNameTv.setText(shoppingCartListBean.getCommodityName());
        childViewHolder.mProductPriceTv.setText(shoppingCartListBean.getPrice() + "");
        Glide.with(childViewHolder.mProductIconIv).load(shoppingCartListBean.getPic())
                //错误
                .error(R.mipmap.ic_launcher)
                //占位图
                .placeholder(R.mipmap.ic_launcher_round)
                //圆角
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(30)))
                .into(childViewHolder.mProductIconIv);

        // TODO: 2020/1/11 给商品设置状态
        childViewHolder.mChildCb.setChecked(shoppingCartListBean.isChecked());

        childViewHolder.mChildCb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 2020/1/11 点击商品checkbox，将商品bean中的状态置反 ，刷新适配器
                boolean checked = shoppingCartListBean.isChecked();
                checked = !checked;
                shoppingCartListBean.setChecked(checked);
                notifyDataSetChanged();
                // TODO: 2020/1/11 通知外接刷新价格
                if (onCartClickListener != null) {
                    onCartClickListener.OnCartClick();
                }
            }
        });

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }


    static class GroupViewHolder {
        @BindView(R.id.seller_cb)
        CheckBox mSellerCb;
        @BindView(R.id.seller_name_tv)
        TextView mSellerNameTv;

        GroupViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    static class ChildViewHolder {
        @BindView(R.id.child_cb)
        CheckBox mChildCb;
        @BindView(R.id.product_icon_iv)
        ImageView mProductIconIv;
        @BindView(R.id.product_title_name_tv)
        TextView mProductTitleNameTv;
        @BindView(R.id.product_price_tv)
        TextView mProductPriceTv;

        ChildViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }


    public float calculateTotalPrice() {
        float totalPrice = 0;
        //遍历商家
        for (int i = 0; i < sellerList.size(); i++) {

            //拿到某个商家
            CartBean.ResultBean resultBean = sellerList.get(i);
            //拿到商家下的所有商品
            List<CartBean.ResultBean.ShoppingCartListBean> shoppingCartList = resultBean.getShoppingCartList();
            //遍历所有商品
            for (int j = 0; j < shoppingCartList.size(); j++) {
                //拿到某个商品
                CartBean.ResultBean.ShoppingCartListBean shoppingCartListBean = shoppingCartList.get(j);
                // TODO: 2020/1/11 只有商品是选中状态的时候，才累计总价
                if (shoppingCartListBean.isChecked()) {
                    totalPrice += shoppingCartListBean.getPrice() * shoppingCartListBean.getCount();
                }
            }
        }
        return totalPrice;
    }


    // TODO: 2020/1/11 计算是否是全部选中

    public boolean calculateIsAllChecked() {
        //默认全部选中
        boolean isAllChecked = true;
        //遍历商家
        for (int i = 0; i < sellerList.size(); i++) {

            //拿到某个商家
            CartBean.ResultBean resultBean = sellerList.get(i);
            //拿到商家下的所有商品
            List<CartBean.ResultBean.ShoppingCartListBean> shoppingCartList = resultBean.getShoppingCartList();
            //遍历所有商品
            for (int j = 0; j < shoppingCartList.size(); j++) {
                //拿到某个商品
                CartBean.ResultBean.ShoppingCartListBean shoppingCartListBean = shoppingCartList.get(j);
                // TODO: 2020/1/11 只要有一个商品未选中，那么全选的状态就是 false
                if (shoppingCartListBean.isChecked() == false) {
                    isAllChecked = false;
                }
            }
        }
        return isAllChecked;
    }


    /**
     * 点击全选的时候使用， 将所有商品设置成某个状态
     */
    public void checkAll(boolean isAllChecked) {
        for (int i = 0; i < sellerList.size(); i++) {
            //拿到某个商家
            CartBean.ResultBean resultBean = sellerList.get(i);
            //拿到商家下的所有商品
            List<CartBean.ResultBean.ShoppingCartListBean> shoppingCartList = resultBean.getShoppingCartList();
            //遍历所有商品
            for (int j = 0; j < shoppingCartList.size(); j++) {
                //拿到某个商品
                CartBean.ResultBean.ShoppingCartListBean shoppingCartListBean = shoppingCartList.get(j);
                // TODO: 2020/1/11 直接改变商品状态
                shoppingCartListBean.setChecked(isAllChecked);
            }
        }
        // TODO: 2020/1/11 刷新适配器
        notifyDataSetChanged();
    }

    OnCartClickListener onCartClickListener;

    public void setOnCartClickListener(OnCartClickListener onCartClickListener) {
        this.onCartClickListener = onCartClickListener;
    }

    public interface OnCartClickListener {
        void OnCartClick();
    }
}
