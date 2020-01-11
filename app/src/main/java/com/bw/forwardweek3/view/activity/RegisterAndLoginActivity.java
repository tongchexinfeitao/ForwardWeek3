package com.bw.forwardweek3.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.blankj.utilcode.util.EncryptUtils;
import com.bw.forwardweek3.R;
import com.bw.forwardweek3.base.BaseActivity;
import com.bw.forwardweek3.contract.IRegisterAndLoginContract;
import com.bw.forwardweek3.model.bean.LoginBean;
import com.bw.forwardweek3.model.bean.RegisterBean;
import com.bw.forwardweek3.presenter.RegisterAndLoginPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterAndLoginActivity extends BaseActivity<RegisterAndLoginPresenter> implements IRegisterAndLoginContract.IView {


    @BindView(R.id.edt_phone)
    EditText mEdtPhone;
    @BindView(R.id.edt_pwd)
    EditText mEdtPwd;
    @BindView(R.id.btn_login)
    Button mBtnLogin;
    @BindView(R.id.btn_register)
    Button mBtnRegister;

    @Override
    protected void initData() {
        Intent intent = new Intent(this, CartActivity.class);
        // TODO: 2020/1/11  带着用户头像跳转
        intent.putExtra("key", "");
        startActivity(intent);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected RegisterAndLoginPresenter providePresenter() {
        return new RegisterAndLoginPresenter();
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void onRegisterSuccess(RegisterBean registerBean) {
        Toast.makeText(RegisterAndLoginActivity.this, "注册成功，请登录", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRegisterFailure(Throwable throwable) {
        Toast.makeText(RegisterAndLoginActivity.this, "注册失败", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onLoginSuccess(LoginBean loginBean) {
        Intent intent = new Intent(this, CartActivity.class);
        // TODO: 2020/1/11  带着用户头像跳转
        intent.putExtra("key", loginBean.getResult().getHeadPic());
        startActivity(intent);
    }

    @Override
    public void onLoginFailure(Throwable throwable) {
        Toast.makeText(RegisterAndLoginActivity.this, "登录失败", Toast.LENGTH_SHORT).show();

    }


    @OnClick({R.id.btn_login, R.id.btn_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                String phone = mEdtPhone.getText().toString();
                String pwd = mEdtPwd.getText().toString();
                //将密码加密
                String s = EncryptUtils.encryptMD5ToString(pwd);
                // TODO: 2020/1/11 用加密过后的密码，进行登录
                mPresenter.login(phone, s);
                break;
            case R.id.btn_register:
                String phone1 = mEdtPhone.getText().toString();
                String pwd1 = mEdtPwd.getText().toString();
                //将密码加密
                String s1 = EncryptUtils.encryptMD5ToString(pwd1);
                // TODO: 2020/1/11 用加密过后的密码，进行注册
                mPresenter.register(phone1, s1);
                break;
        }
    }
}
