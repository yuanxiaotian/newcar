package com.xuliucar.newcar.adapter

import com.cangmaomao.recyclerview.adapter.CMMAdapter
import com.cangmaomao.recyclerview.adapter.CMMViewHolder
import com.xuliucar.newcar.R
import com.xuliucar.newcar.bean.NoticeBean

/**
 * Created by skyward on 2016/7/15.
 *
 */
class NoticeAdapter(layoutId: Int = R.layout.item_notice) : CMMAdapter<NoticeBean.NoticeList>(layoutId) {

    override fun convert(holder: CMMViewHolder, p1: NoticeBean.NoticeList, p2: Int) {
        holder.setText(R.id.notice_title,p1.title)
        holder.setText(R.id.notice_scon,p1.scon)
        holder.setText(R.id.notice_time,p1.time)

    }
}
