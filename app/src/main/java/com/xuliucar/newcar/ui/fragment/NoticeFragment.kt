package com.xuliucar.newcar.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.View
import com.cangmaomao.lib.base.BaseNewFragment
import com.cangmaomao.lib.utils.SPUtils.create
import com.cangmaomao.lib.utils.initRecycler
import com.cangmaomao.recyclerview.adapter.CMMAdapter
import com.cangmaomao.recyclerview.adapter.contract.OnItemClick
import com.xuliucar.newcar.presenter.NoticePresenter
import com.xuliucar.newcar.R
import com.xuliucar.newcar.adapter.NoticeAdapter
import com.xuliucar.newcar.bean.NoticeBean
import com.xuliucar.newcar.contract.NoticeContract
import kotlinx.android.synthetic.main.fragment_notice.*

/**
 * Created by skyward on 2016/6/29.
 */
class NoticeFragment : BaseNewFragment<NoticeContract.Presenter>(), NoticeContract.View, OnItemClick {

    private lateinit var adapter: NoticeAdapter
    override fun layViewId(): Int = R.layout.fragment_notice
    override fun addViewId(): Int = 0
    override fun context(): Context = _mActivity
    override fun companyId(): String = create("userInfo").get("compid", "") as String
    override fun loginId(): String = create("userInfo").get("loginid", "") as String


    override fun initView(savedInstanceState: Bundle?, view: View) {
        initRecycler(recyclerView)
        adapter = NoticeAdapter()
        recyclerView.adapter = adapter
        adapter.setOnItemClickListener(this)
        NoticePresenter(this)
        p.start()
    }

    override fun showData(data: MutableList<NoticeBean.NoticeList>) {
        adapter.addList(data)
    }

    override fun showFail(e: Throwable) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    override fun onItemClick(p0: CMMAdapter<*>?, p1: Any?, p2: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


//    override fun onItemClick(adapterView: AdapterView<*>, v: View, i: Int, l: Long) {
//        val noticeBean = mNoticeLists!![i]
//        val type = noticeBean.type
//        val title = noticeBean.title
//        val scon = noticeBean.scon
//        if (noticeBean.url != "") {
//            val url = noticeBean.url
//            val isPDF = url.substring(url.length - 3, url.length)
//            if (type == "0") {
//                val intent = Intent(activity, Web::class.java)
//                intent.putExtra("url", url)
//                intent.putExtra("title", title)
//                intent.putExtra("con", scon)
//                startActivity(intent)
//            } else if (type == "1" && isPDF == "pdf") {
//                val intent2 = Intent(activity, OpenPDF::class.java)
//                intent2.putExtra("url", url)
//                intent2.putExtra("title", title)
//                intent2.putExtra("con", scon)
//                startActivity(intent2)
//            } else if (type == "1") {
//                val str = url.substring(url.length - 3, url.length)
//                if (str == "doc") {
//                    val intent3 = Intent(activity, World::class.java)
//                    intent3.putExtra("url", url)
//                    intent3.putExtra("title", title)
//                    intent3.putExtra("con", scon)
//                    startActivity(intent3)
//                } else {
//                    val intent4 = Intent(activity, FileReadActivity::class.java)
//                    intent4.putExtra("url", url)
//                    intent4.putExtra("title", title)
//                    intent4.putExtra("con", scon)
//                    startActivity(intent4)
//                }
//            }
//        }
//        if (type == "2") {
//            val intent5 = Intent(activity, OtherNotices::class.java)
//            intent5.putExtra("con", noticeBean.con)
//            startActivity(intent5)
//        }
//    }

}
