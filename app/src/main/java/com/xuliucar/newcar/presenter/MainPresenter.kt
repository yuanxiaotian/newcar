package com.xuliucar.newcar.presenter

import com.cangmaomao.network.request.HttpManage
import com.cangmaomao.network.request.utils.RxSchedulers
import com.xuliucar.me.api.ApiConfig
import com.xuliucar.newcar.contract.MainContract
import com.xuliucar.me.model.CompanyInfo

class MainPresenter(val view: MainContract.View) : MainContract.Presenter {


    init {
        view.setPresenter(this)
    }


    override fun start() {
        loadData()
    }

    override fun loadData() {
        HttpManage.getInstance().create(ApiConfig::class.java)
                .companyInfo(view.loginId(), view.companyId())
                .compose(RxSchedulers.io_main())
                .subscribe({ disposeData(it) }, { view.showFail(it) })
    }

    override fun disposeData(bean: Any?) {
        if (bean != null) {
            bean as CompanyInfo
            view.loginOverdue(bean.data == null)
        }
    }
}