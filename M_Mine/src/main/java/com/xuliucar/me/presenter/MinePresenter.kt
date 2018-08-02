package com.xuliucar.me.presenter

import com.cangmaomao.lib.config.AppPower
import com.cangmaomao.network.request.HttpManage
import com.cangmaomao.network.request.utils.RxSchedulers
import com.xuliucar.me.api.ApiConfig
import com.xuliucar.me.contract.MineContract
import com.xuliucar.me.model.LoginInfo

class MinePresenter(val view: MineContract.LoginView) : MineContract.Presenter {

    init {
        this.view.setPresenter(this)
    }

    override fun start() {
        loadData()
    }


    override fun loadData() {
        HttpManage.getInstance()
                .create(ApiConfig::class.java)
                .getLogin(view.getCompany(), view.getAccount(), view.getPwd())
                .compose(RxSchedulers.io_main())
                .subscribe({ disposeData(it) }, { view.showFail(it) })
    }

    override fun disposeData(bean: LoginInfo?) {
        if (bean != null) {
            if (bean.isSuccess) view.showSuccess(bean) else view.showFail(Throwable(bean.message))
        } else {
            view.showFail(Throwable("请求出错,请稍后重试!"))
        }
    }

}