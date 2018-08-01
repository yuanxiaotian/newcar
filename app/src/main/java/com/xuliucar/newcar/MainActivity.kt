package com.xuliucar.newcar

import com.cangmaomao.lib.base.BaseNewActivity
import com.cangmaomao.lib.base.BasePresenter

class MainActivity : BaseNewActivity<BasePresenter>() {
    override fun layViewId(): Int = R.layout.activity_main

    override fun addViewId(): Int = 0

    override fun initView() {
    }

}
