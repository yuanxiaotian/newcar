package com.xuliucar.carlocation.ui

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import android.support.v7.widget.AppCompatButton
import android.support.v7.widget.AppCompatImageView
import android.view.View
import com.amap.api.maps.AMap
import com.cangmaomao.lib.base.BaseNewFragment
import com.xuliucar.carlocation.R
import com.xuliucar.carlocation.contract.CarLocationContract
import kotlinx.android.synthetic.main.fragment_car_location_map.*

class CarLocationMapFragment : BaseNewFragment<CarLocationContract.LocationMapPresenter>(), CarLocationContract.LocationMapView {

    private lateinit var aMap: AMap
    private lateinit var locationView: View
    private var dialog: AlertDialog? = null

    override fun layViewId(): Int = R.layout.fragment_car_location_map
    override fun addViewId(): Int = 0

    override fun initView(savedInstanceState: Bundle?, view: View) {
        initToolbar(initArguments("id", ""), null)
        mapView.onCreate(savedInstanceState)
        aMap = mapView.map
        aMap.isMyLocationEnabled = false
        aMap.uiSettings.setLogoBottomMargin(-100)
        aMap.uiSettings.isZoomControlsEnabled = false


        location_type.setOnCheckedChangeListener { _, checkedId ->
            initShowLoginView(checkedId)
        }
        location_type.check(R.id.real_time)

        locationView.findViewById<AppCompatImageView>(R.id.iv_share).setOnClickListener { initShareDialog() }
    }

    @SuppressLint("InflateParams")
    override fun initShareDialog() {
        if (dialog == null) {
            dialog = AlertDialog.Builder(_mActivity).create()
            val view = layoutInflater.inflate(R.layout.fragment_location_dialog, null)
            view.findViewById<AppCompatButton>(R.id.bt_cancel).setOnClickListener { dialog!!.dismiss() }
            dialog!!.setView(view)
        }
        dialog!!.show()
    }

    override fun initShowLoginView(id: Int) {
        when (id) {
            R.id.real_time -> {
                location_box.removeAllViews()
                locationView = View.inflate(_mActivity, R.layout.impl_real_time, null)
                location_box.addView(locationView)
                location_box_bg.setBackgroundResource(R.drawable.shishi_img_bg)
            }
            R.id.history_trajectory -> {
                location_box.removeAllViews()
                locationView = View.inflate(_mActivity, R.layout.impl_history_trajectory, null)
                location_box.addView(locationView)
                location_box_bg.setBackgroundResource(R.drawable.login_history_bg)
            }
        }
    }
}