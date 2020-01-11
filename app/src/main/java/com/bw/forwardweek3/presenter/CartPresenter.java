package com.bw.forwardweek3.presenter;

import com.bw.forwardweek3.base.BasePresenter;
import com.bw.forwardweek3.contract.ICartContract;
import com.bw.forwardweek3.model.CartModel;
import com.bw.forwardweek3.model.bean.CartBean;

public class CartPresenter extends BasePresenter<ICartContract.IView> implements ICartContract.IPresenter {

    private CartModel cartModel;

    @Override
    protected void initModel() {
        cartModel = new CartModel();
    }

    @Override
    public void getCartData(int page) {
        cartModel.getCartData(page, new ICartContract.IModel.IModelCallback() {
            @Override
            public void onCartSuccess(CartBean cartBean) {
                view.onCartSuccess(cartBean);
            }

            @Override
            public void onCartFailure(Throwable throwable) {
                view.onCartFailure(throwable);
            }
        });
    }
}
