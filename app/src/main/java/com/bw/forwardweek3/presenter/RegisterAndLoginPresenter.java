package com.bw.forwardweek3.presenter;


import com.bw.forwardweek3.base.BasePresenter;
import com.bw.forwardweek3.contract.IRegisterAndLoginContract;
import com.bw.forwardweek3.model.RegisterAndLoginModel;
import com.bw.forwardweek3.model.bean.LoginBean;
import com.bw.forwardweek3.model.bean.RegisterBean;

public class RegisterAndLoginPresenter extends BasePresenter<IRegisterAndLoginContract.IView> implements IRegisterAndLoginContract.IPresenter {

    private RegisterAndLoginModel registerAndLoginModel;

    @Override
    protected void initModel() {
        registerAndLoginModel = new RegisterAndLoginModel();
    }

    @Override
    public void register(String phone, String pwd) {
        registerAndLoginModel.register(phone, pwd, new IRegisterAndLoginContract.IModel.IModelCallback() {
            @Override
            public void onRegisterSuccess(RegisterBean registerBean) {
                view.onRegisterSuccess(registerBean);
            }

            @Override
            public void onRegisterFailure(Throwable throwable) {
                view.onRegisterFailure(throwable);
            }

            @Override
            public void onLoginSuccess(LoginBean loginBean) {
                view.onLoginSuccess(loginBean);
            }

            @Override
            public void onLoginFailure(Throwable throwable) {
                view.onLoginFailure(throwable);
            }
        });
    }

    @Override
    public void login(String phone, String pwd) {
        registerAndLoginModel.login(phone, pwd, new IRegisterAndLoginContract.IModel.IModelCallback() {
            @Override
            public void onRegisterSuccess(RegisterBean registerBean) {
                view.onRegisterSuccess(registerBean);
            }

            @Override
            public void onRegisterFailure(Throwable throwable) {
                view.onRegisterFailure(throwable);
            }

            @Override
            public void onLoginSuccess(LoginBean loginBean) {
                view.onLoginSuccess(loginBean);
            }

            @Override
            public void onLoginFailure(Throwable throwable) {
                view.onLoginFailure(throwable);
            }
        });
    }
}
