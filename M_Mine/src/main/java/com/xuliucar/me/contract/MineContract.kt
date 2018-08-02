package com.xuliucar.me.contract

import com.cangmaomao.lib.base.BasePresenter
import com.cangmaomao.lib.base.BaseView
import com.xuliucar.me.model.LoginInfo

interface MineContract {

    interface LoginView : BaseView<Presenter> {

        fun getCompany(): String

        fun getAccount(): String

        fun getPwd(): String

        //显示部分

        fun showSuccess(data:LoginInfo)

        fun showFail(e: Throwable)

    }


    interface Presenter : BasePresenter {

        fun loadData()

        fun disposeData(bean: LoginInfo?)

    }

}