package com.xuliucar.me.contract

import android.os.Bundle
import android.view.View
import com.cangmaomao.lib.base.BasePresenter
import com.cangmaomao.lib.base.BaseView

interface IrregularitiesContract {


    /**
     * 主
     */
    interface View : BaseView<Presenter> {
        fun initDropDownPop()

        fun toFragmentBundle(): Bundle

        fun checkValNull(view: android.view.View): String
    }

    interface Presenter : BasePresenter {

        fun loadData()

        fun disposeData(bean: Any)

    }


    /**
     * 详情
     */
    interface DetailsView : BaseView<DetailsPresenter> {


    }

    interface DetailsPresenter : BasePresenter {

        fun loadData()

        fun disposeData(bean: Any)

    }

}