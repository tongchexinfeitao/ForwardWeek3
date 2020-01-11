package com.bw.forwardweek3.contract;


import com.bw.forwardweek3.model.bean.CartBean;

public interface ICartContract {
    interface IView {
        void onCartSuccess(CartBean cartBean);

        void onCartFailure(Throwable throwable);
    }

    interface IPresenter {
        // TODO: 2020/1/11 请求购物车的时候， 需要指定页数
        void getCartData(int page);
    }

    interface IModel {
        void getCartData(int page, IModelCallback iModelCallback);

        interface IModelCallback {
            void onCartSuccess(CartBean cartBean);

            void onCartFailure(Throwable throwable);
        }
    }
}
