package com.xuliucar.carlocation.adapter

import android.support.constraint.ConstraintLayout
import com.cangmaomao.recyclerview.adapter.CMMAdapter
import com.cangmaomao.recyclerview.adapter.CMMViewHolder
import com.xuliucar.carlocation.R
import com.xuliucar.carlocation.bean.CarsListBean

class CarListAdapter(layoutId: Int = R.layout.item_fragment_car_list) : CMMAdapter<CarsListBean.DataBean.InfoBean>(layoutId) {

    override fun convert(holder: CMMViewHolder, item: CarsListBean.DataBean.InfoBean, p2: Int) {
        holder.setText(R.id.tv_name, item.id)
        val view = holder.getView<ConstraintLayout>(R.id.item_view)
        val id = holder.mContext.resources.getColor(if (item.isSelect) R.color.c_1e else R.color.c_w)
        view.setBackgroundColor(id)
    }
}