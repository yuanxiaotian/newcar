package com.xuliucar.me.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import com.cangmaomao.lib.base.BaseNewFragment
import com.xuliucar.me.R
import com.xuliucar.me.contract.IrregularitiesContract
import com.xuliucar.me.ui.pop.CarTypePopView
import kotlinx.android.synthetic.main.fragment_irregularities.*

class IrregularitiesDetailsFragment : BaseNewFragment<IrregularitiesContract.DetailsPresenter>(), IrregularitiesContract.DetailsView {

    override fun layViewId(): Int = R.layout.fragment_irregularities_details
    override fun addViewId(): Int = 0

    override fun initView(savedInstanceState: Bundle?, view: View) {
        initToolbar(initArguments("a", ""), null)
    }
}