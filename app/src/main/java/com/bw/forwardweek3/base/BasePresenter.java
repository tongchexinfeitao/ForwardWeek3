package com.bw.forwardweek3.base;

public abstract class BasePresenter<V> {
    protected V view;

    //绑定
    public void attach(V view) {
        this.view = view;
    }

    //解绑
    public void dettach() {
        view = null;
    }

    public BasePresenter() {
        initModel();
    }

    protected abstract void initModel();
}
