package com.xuliucar.car.contract

import android.content.Context
import com.cangmaomao.lib.base.BasePresenter
import com.cangmaomao.lib.base.BaseView
import com.xuliucar.newcar.bean.HomeBanner
import com.xuliucar.newcar.bean.NoticeBean

interface HomePageContract {


    /**
     * 主
     */
    interface View : BaseView<Presenter> {

        //获取部分
        fun context(): Context

        fun loginId(): String
        fun companyId(): String

        //显示部分
        fun showFail(e: Throwable)

        fun toItemFragment(flag: String, appPower: String)

        fun showBanner(bannerList: MutableList<String>)

        fun showNotice(noticeList: MutableList<NoticeBean.NewNoticeData>)

    }

    interface Presenter : BasePresenter {

        fun loadData()

        fun disposeBanner(bean: HomeBanner)

        fun disposeNotice(bean: NoticeBean)

    }
}
