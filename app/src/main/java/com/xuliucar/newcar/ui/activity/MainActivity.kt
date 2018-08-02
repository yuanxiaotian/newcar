package com.xuliucar.newcar.ui.activity

import com.cangmaomao.lib.action.*
import com.cangmaomao.lib.base.BaseActivity
import com.cangmaomao.lib.base.BasePresenter
import com.cangmaomao.lib.event.AppEvent
import com.cangmaomao.lib.utils.StatusBarUtil
import com.xuliucar.carlocation.contract.CarLocationContract
import com.xuliucar.carlocation.ui.CarLocationFragment
import com.xuliucar.newcar.ui.fragment.MainFragment
import com.xuliucar.me.ui.fragment.*
import com.xuliucar.newcar.R
import me.yokeyword.fragmentation.anim.DefaultNoAnimator
import me.yokeyword.fragmentation.anim.FragmentAnimator
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class MainActivity : BaseActivity<BasePresenter>() {

    override fun layViewId(): Int = R.layout.activity_main
    override fun addViewId(): Int = R.id.frameLayout

    override fun initView() {
        StatusBarUtil.transparencyBar(this)
        StatusBarUtil.setStatusBarColor(this, R.color.c_1e)
        val flag = intent.getIntExtra("flag", 0)
        loadRootFragment(addViewId(), if (flag == 110) LoginFragment() else MainFragment(), false, false)
    }

    override fun onCreateFragmentAnimator(): FragmentAnimator {
        return DefaultNoAnimator()
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(event: AppEvent) {
        when (event.flag) {
            f_main -> loadRootFragment(addViewId(), MainFragment(), false, false)
            f_login -> start(LoginFragment())
            f_reg -> start(RegFragment())
            f_feedBack -> start(FeedBack())
            f_informationCenter -> start(InformationCenter())
            f_carLocationFragment -> start(CarLocationFragment())
            f_peopleList -> {
                val c = PeopleList()
                c.arguments = event.bundle
                start(c)
            }
            f_peopleDetail -> {
                val c = PeopleDetail()
                c.arguments = event.bundle
                start(c)
            }
            f_showBigImage -> {
                val c = ShowBigImage()
                c.arguments = event.bundle
                start(c)
            }
        }
    }

    override fun onBackPressedSupport() {
        when (topFragment) {
            is MainFragment -> finish()
            is LoginFragment -> finish()
            else -> {
                pop()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

}
