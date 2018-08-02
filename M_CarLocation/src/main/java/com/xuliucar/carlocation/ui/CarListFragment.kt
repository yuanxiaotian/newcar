package com.xuliucar.carlocation.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import com.cangmaomao.lib.base.BaseNewFragment
import com.cangmaomao.lib.utils.initRecycler
import com.cangmaomao.lib.utils.toast
import com.cangmaomao.recyclerview.adapter.CMMAdapter
import com.cangmaomao.recyclerview.adapter.contract.OnItemClick
import com.xuliucar.carlocation.R
import com.xuliucar.carlocation.adapter.CarListAdapter
import com.xuliucar.carlocation.bean.CarsListBean
import com.xuliucar.carlocation.contract.CarLocationContract
import com.xuliucar.carlocation.presenter.CarListPresenter
import kotlinx.android.synthetic.main.fragment_car_list.*

class CarListFragment : BaseNewFragment<CarLocationContract.CarListPresenter>(), CarLocationContract.CarListView, OnItemClick {

    private lateinit var mAdapter: CarListAdapter

    override fun layViewId(): Int = R.layout.fragment_car_list
    override fun addViewId(): Int = 0
    override fun context(): Context = _mActivity
    override fun loginId(): String = initArguments("loginId", "")
    override fun companyId(): String = initArguments("companyId", "")

    override fun initView(savedInstanceState: Bundle?, view: View) {
        initRecycler(recyclerView)
        mAdapter = CarListAdapter()
        mAdapter.setOnItemClickListener(this)
        recyclerView.adapter = mAdapter

        CarListPresenter(this)
        p.start()
    }

    override fun showData(data: MutableList<CarsListBean.DataBean.InfoBean>) {
        mAdapter.addList(data)
    }

    override fun onItemClick(p0: CMMAdapter<*>?, p1: Any?, p2: Int) {
        val list = mAdapter.data[p2]
        if (!list.isShare) {
            list.isSelect = !list.isSelect
        } else {
            toast("当前车辆已经分享过!")
        }
        mAdapter.notifyItemChanged(p2)
    }

    override fun showFail(e: Throwable) {
        toast(e.message!!)
    }

}