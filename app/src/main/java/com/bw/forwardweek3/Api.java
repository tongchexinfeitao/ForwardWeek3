package com.bw.forwardweek3;

import com.bw.forwardweek3.model.bean.CartBean;
import com.bw.forwardweek3.model.bean.LoginBean;
import com.bw.forwardweek3.model.bean.RegisterBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Api {

    @FormUrlEncoded
    @POST("small/user/v1/register")
    Observable<RegisterBean> register(@Field("phone") String phone, @Field("pwd") String pwd);


    @FormUrlEncoded
    @POST("small/user/v1/login")
    Observable<LoginBean> login(@Field("phone") String phone, @Field("pwd") String pwd);


    @GET("small/order/verify/v1/findShoppingCart")
    Observable<CartBean> getCartData(@Header("userId") int userId,
                                     @Header("sessionId") String sessionId);

}
