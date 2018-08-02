package com.xuliucar.carlocation.ui

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import android.support.v7.widget.AppCompatImageView
import android.view.View
import com.cangmaomao.lib.base.BaseNewFragment
import com.cangmaomao.lib.base.BasePresenter
import com.cangmaomao.lib.utils.SPUtils.create
import com.xuliucar.carlocation.R
import com.xuliucar.carlocation.contract.CarLocationContract
import kotlinx.android.synthetic.main.fragment_car_location.*

class CarLocationFragment : BaseNewFragment<CarLocationContract.Presenter>(), CarLocationContract.View {

    private lateinit var rightView: AppCompatImageView
    private val titleList = listOf("全部", "未分享车辆", "已分享车辆")

    override fun layViewId(): Int = R.layout.fragment_car_location
    override fun addViewId(): Int = 0
    override fun context(): Context = _mActivity
    override fun companyId(): String = create("userInfo").get("compid", "") as String
    override fun loginId(): String = create("userInfo").get("loginid", "") as String

    override fun initView(savedInstanceState: Bundle?, view: View) {
        rightView = initToolbar(getString(R.string.car_location), null, R.drawable.location_batch_but)


        val list = initChildFragment()
        viewPager.adapter = object : FragmentPagerAdapter(childFragmentManager) {
            override fun getItem(position: Int): Fragment {
                return list[position]
            }

            override fun getCount(): Int {
                return list.size
            }

            override fun getPageTitle(position: Int): CharSequence? {
                return titleList[position]
            }
        }
        viewPager.offscreenPageLimit = titleList.size
        tl.setupWithViewPager(viewPager)

    }

    override fun initChildFragment(): MutableList<Fragment> {
        val list = ArrayList<Fragment>()
        for (i in 1..3) {
            val c = CarListFragment()
            val bundle = Bundle()
            bundle.putString("loginId", loginId())
            bundle.putString("companyId", companyId())
            c.arguments = bundle
            list.add(c)
        }
        return list
    }

}