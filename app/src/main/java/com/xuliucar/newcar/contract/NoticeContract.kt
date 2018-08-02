package com.xuliucar.newcar.contract

import android.content.Context
import com.cangmaomao.lib.base.BasePresenter
import com.cangmaomao.lib.base.BaseView
import com.xuliucar.me.model.NoteBean
import com.xuliucar.newcar.bean.NoticeBean

interface NoticeContract {


    /**
     * 主
     */
    interface View : BaseView<Presenter> {

        //获取部分
        fun context(): Context

        fun loginId(): String

        fun companyId():String

        //显示部分
        fun showFail(e: Throwable)

        fun showData(data: MutableList<NoticeBean.NoticeList>)

    }

    interface Presenter : BasePresenter {
        fun loadData()

        fun disposeData(bean: Any?)
    }

}