package com.xuliucar.carlocation.ui

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.support.v7.widget.AppCompatButton
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
import com.xuliucar.carlocation.event.LocationEvent
import com.xuliucar.carlocation.presenter.CarListPresenter
import kotlinx.android.synthetic.main.fragment_car_list.*
import org.greenrobot.eventbus.EventBus

class CarListFragment : BaseNewFragment<CarLocationContract.CarListPresenter>(), CarLocationContract.CarListView, OnItemClick {

    private var dialog: AlertDialog? = null
    private lateinit var mAdapter: CarListAdapter
    override fun layViewId(): Int = R.layout.fragment_car_list
    override fun addViewId(): Int = 0
    override fun context(): Context = _mActivity
    override fun loginId(): String = initArguments("loginId", "")
    override fun companyId(): String = initArguments("companyId", "")

    override fun initView(savedInstanceState: Bundle?, view: View) {
        initRecycler(recyclerView)
        mAdapter = CarListAdapter(this)
        mAdapter.setOnItemClickListener(this)
        recyclerView.adapter = mAdapter

        CarListPresenter(this)
        p.start()
    }

    @SuppressLint("InflateParams")
    override fun initShareDialog() {
        if (dialog == null) {
            dialog = AlertDialog.Builder(context()).create()
            val view = layoutInflater.inflate(R.layout.fragment_location_dialog, null)
            view.findViewById<AppCompatButton>(R.id.bt_cancel).setOnClickListener { dialog!!.dismiss() }
            dialog!!.setView(view)
        }
        dialog!!.show()
    }

    override fun showData(data: MutableList<CarsListBean.DataBean.InfoBean>) {
        mAdapter.addList(data)
    }

    override fun onItemClick(p0: CMMAdapter<*>?, p1: Any?, p2: Int) {
        val list = mAdapter.data[p2]
        if (!list.isShare) {
            list.isSelect = !list.isSelect
            EventBus.getDefault().post(LocationEvent(list.isSelect, list))
        } else {
            toast("当前车辆已经分享过!")
        }
        mAdapter.notifyItemChanged(p2)
    }

    override fun showFail(e: Throwable) {
        toast(e.message!!)
    }

}