package com.xuliucar.me.contract

import android.os.Bundle
import com.cangmaomao.lib.base.BasePresenter
import com.cangmaomao.lib.base.BaseView

interface UpdatePasswordContract {


    /**
     * 主
     */
    interface View : BaseView<Presenter> {


        fun checkValNull(view: android.view.View): String
    }

    interface Presenter : BasePresenter {

        fun loadData()

        fun disposeData(bean: Any)

    }
}