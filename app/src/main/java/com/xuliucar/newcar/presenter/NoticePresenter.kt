package com.xuliucar.newcar.presenter

import com.cangmaomao.network.request.HttpManage
import com.cangmaomao.network.request.utils.RxSchedulers
import com.xuliucar.newcar.api.ApiConfig
import com.xuliucar.newcar.bean.NoticeBean
import com.xuliucar.newcar.contract.NoticeContract


class NoticePresenter(val view: NoticeContract.View) : NoticeContract.Presenter {

    init {
        view.setPresenter(this)
    }

    override fun start() {
        loadData()
    }


    override fun loadData() {
        HttpManage.getInstance()
                .create(ApiConfig::class.java)
                .getNotices(view.loginId(), view.companyId())
                .compose(RxSchedulers.io_main())
                .subscribe({ disposeData(it) }, { view.showFail(it) })
    }

    override fun disposeData(bean: Any?) {
        if (bean != null) {
            bean as NoticeBean
            if (bean.success) view.showData(bean.data) else view.showFail(Throwable(bean.message))
        } else {
            view.showFail(Throwable("请求错误,请稍后重试!"))
        }
    }


}