package com.xuliucar.newcar.api

import com.xuliucar.me.model.*
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.POST
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Url
import com.xuliucar.newcar.bean.NoticeBean
import com.xuliucar.newcar.bean.HomeBanner


interface ApiConfig {

    //公告列表
    @FormUrlEncoded
    @POST("notice_list")
    fun getNotices(@Field("loginid") loginid: String, @Field("compid") compid: String): Observable<NoticeBean>


    @FormUrlEncoded//首页banner
    @POST("indexbanner")
    fun indexbanner(@Field("loginid") loginid: String): Observable<HomeBanner>

}