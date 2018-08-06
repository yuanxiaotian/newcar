package com.xuliucar.carlocation.adapter

import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.AppCompatImageView
import android.support.v7.widget.AppCompatTextView
import com.cangmaomao.lib.action.f_carLocationMapFragment
import com.cangmaomao.lib.event.AppEvent
import com.cangmaomao.recyclerview.adapter.CMMAdapter
import com.cangmaomao.recyclerview.adapter.CMMViewHolder
import com.xuliucar.carlocation.R
import com.xuliucar.carlocation.bean.CarsListBean
import com.xuliucar.carlocation.contract.CarLocationContract
import org.greenrobot.eventbus.EventBus

class CarListAdapter(val view: CarLocationContract.CarListView, layoutId: Int = R.layout.item_fragment_car_list) : CMMAdapter<CarsListBean.DataBean.InfoBean>(layoutId) {

    override fun convert(holder: CMMViewHolder, item: CarsListBean.DataBean.InfoBean, p2: Int) {
        val itemView = holder.getView<ConstraintLayout>(R.id.item_view)
        val name = holder.getView<AppCompatTextView>(R.id.tv_name)
        val number = holder.getView<AppCompatTextView>(R.id.tv_number)
        val location = holder.getView<AppCompatImageView>(R.id.tv_location)
        val share = holder.getView<AppCompatImageView>(R.id.tv_share)
        name.text = item.id
        val textBg = holder.mContext.resources.getColor(if (item.isSelect) R.color.c_w else R.color.c_2c)
        itemView.setBackgroundResource(if (item.isSelect) R.color.c_1e else R.color.c_w)
        name.setTextColor(textBg)
        number.setTextColor(textBg)
        location.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("id", number.text.toString())
            EventBus.getDefault().post(AppEvent(f_carLocationMapFragment, bundle))
        }
        share.setOnClickListener { view.initShareDialog() }

    }
}