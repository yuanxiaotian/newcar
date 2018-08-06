package com.xuliucar.me.ui.fragment

import android.os.Bundle
import android.view.View
import com.cangmaomao.lib.base.BaseNewFragment
import com.cangmaomao.lib.base.BasePresenter
import com.xuliucar.me.R
import com.xuliucar.me.contract.UpdatePasswordContract

class UpdatePasswordFragment : BaseNewFragment<UpdatePasswordContract.Presenter>(), UpdatePasswordContract.View {

    override fun layViewId(): Int = R.layout.fragment_update_password

    override fun addViewId(): Int = 0

    override fun initView(savedInstanceState: Bundle?, view: View) {
        initToolbar(getString(R.string.alert_pwd), null)
    }

    override fun checkValNull(view: View): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}