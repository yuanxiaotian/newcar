package com.xuliucar.newcar.ui.fragment

import android.content.Context
import android.content.res.ColorStateList
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.view.View
import com.cangmaomao.lib.action.f_login
import com.cangmaomao.lib.base.BaseNewFragment
import com.cangmaomao.lib.event.AppEvent
import com.cangmaomao.lib.event.MainEvent
import com.cangmaomao.lib.utils.SPUtils.create
import com.xuliucar.newcar.contract.MainContract
import com.xuliucar.newcar.presenter.MainPresenter
import com.xuliucar.me.ui.fragment.Me
import com.xuliucar.newcar.R
import com.xuliucar.newcar.app.App
import kotlinx.android.synthetic.main.fragment_main.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class MainFragment : BaseNewFragment<MainContract.Presenter>(), MainContract.View {

    override fun layViewId(): Int = R.layout.fragment_main
    override fun addViewId(): Int = R.id.main_frameLayout
    override fun context(): Context = _mActivity
    override fun companyId(): String = create("userInfo").get("compid", "") as String
    override fun loginId(): String = create("userInfo").get("loginid", "") as String

    private lateinit var homePage: HomePageFragment
    private lateinit var notice: NoticeFragment
    private lateinit var me: Me


    override fun initView(savedInstanceState: Bundle?, view: View) {
        homePage = HomePageFragment()
        notice = NoticeFragment()
        me = Me()
        MainPresenter(this)
        p.start()
    }

    override fun loginOverdue(overdue: Boolean) {
        if (overdue) {
            _mActivity.create("userInfo").removeAll()
            EventBus.getDefault().post(AppEvent(f_login, null))
        } else {
            loadMultipleRootFragment(addViewId(), 0, homePage, notice, me)
            val state = arrayOf(intArrayOf(-android.R.attr.state_checked), intArrayOf(android.R.attr.state_checked))
            val colors = intArrayOf(resources.getColor(R.color.c_7c), resources.getColor(R.color.c_1e))
            val colorStateList = ColorStateList(state, colors)
            navigation.itemTextColor = colorStateList
            navigation.itemIconTintList = colorStateList
            navigation.setOnNavigationItemSelectedListener(mOnNavigationListener)
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(event: MainEvent) {
        when (event.type) {
            0 -> {
                val a = (_mActivity.application as App).activitys
                for (activity in a) {
                    activity.finish()
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    private val mOnNavigationListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                showHideFragment(homePage)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_gg -> {
                showHideFragment(notice)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_me -> {
                showHideFragment(me)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun showFail(e: Throwable) {
    }

}
