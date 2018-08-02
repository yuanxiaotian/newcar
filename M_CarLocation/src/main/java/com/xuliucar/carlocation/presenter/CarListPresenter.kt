package com.xuliucar.carlocation.presenter

import com.cangmaomao.network.request.HttpManage
import com.cangmaomao.network.request.utils.RxSchedulers
import com.xuliucar.carlocation.api.ApiConfig
import com.xuliucar.carlocation.bean.CarsListBean
import com.xuliucar.carlocation.contract.CarLocationContract


class CarListPresenter(val view: CarLocationContract.CarListView) : CarLocationContract.CarListPresenter {

    init {
        view.setPresenter(this)
    }

    override fun start() {
        loadData()
    }


    override fun loadData() {
        HttpManage.getInstance()
                .create(ApiConfig::class.java)
                .carList(view.loginId(), view.companyId())
                .compose(RxSchedulers.io_main())
                .subscribe({ disposeData(it) }, { view.showFail(it) })
    }

    override fun disposeData(bean: Any?) {
        if (bean != null) {
            bean as CarsListBean
            if (bean.isSuccess) view.showData(bean.data.info) else view.showFail(Throwable(bean.message))
        } else {
            view.showFail(Throwable("请求错误,请稍后重试!"))
        }
    }


}