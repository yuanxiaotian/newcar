package com.xuliucar.carlocation.contract

import android.content.Context
import android.support.v4.app.Fragment
import com.cangmaomao.lib.base.BasePresenter
import com.cangmaomao.lib.base.BaseView
import com.xuliucar.carlocation.bean.CarsListBean

interface CarLocationContract {


    /**
     * 主
     */
    interface View : BaseView<Presenter> {
        fun context(): Context

        fun loginId(): String
        fun companyId(): String
        fun initChildFragment(): MutableList<Fragment>
    }

    interface Presenter : BasePresenter {
    }

    /**
     * 子View CarList
     */
    interface CarListView : BaseView<CarListPresenter> {
        fun context(): Context

        fun loginId(): String
        fun companyId(): String

        fun showData(data: MutableList<CarsListBean.DataBean.InfoBean>)
        fun showFail(e: Throwable)
    }

    interface CarListPresenter : BasePresenter {
        fun loadData()
        fun disposeData(bean: Any?)
    }

}