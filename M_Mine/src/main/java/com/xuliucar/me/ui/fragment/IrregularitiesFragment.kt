package com.xuliucar.me.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.widget.AppCompatTextView
import android.text.TextUtils
import android.view.View
import android.widget.TextView
import com.cangmaomao.lib.action.f_irregularitiesDetailsFragment
import com.cangmaomao.lib.base.BaseNewFragment
import com.cangmaomao.lib.event.AppEvent
import com.cangmaomao.lib.utils.toast
import com.xuliucar.me.R
import com.xuliucar.me.contract.IrregularitiesContract
import com.xuliucar.me.ui.pop.CarTypePopView
import kotlinx.android.synthetic.main.fragment_irregularities.*
import org.greenrobot.eventbus.EventBus

class IrregularitiesFragment : BaseNewFragment<IrregularitiesContract.Presenter>(), IrregularitiesContract.View {

    private var carNature = "1"
    private var popView: CarTypePopView? = null

    override fun layViewId(): Int = R.layout.fragment_irregularities
    override fun addViewId(): Int = 0

    override fun initView(savedInstanceState: Bundle?, view: View) {
        initToolbar(getString(R.string.illegal_query), null)

        tv_type.setOnClickListener { initDropDownPop() }

        start_query.setOnClickListener {
            try {
                val bundle = toFragmentBundle()
                EventBus.getDefault().post(AppEvent(f_irregularitiesDetailsFragment, bundle))
            } catch (e: IllegalStateException) {
                toast(e.message!!)
            }
        }

        rg_car.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.company -> carNature = "1"
                R.id.personal -> carNature = "2"
            }
        }
    }

    @SuppressLint("InflateParams")
    override fun initDropDownPop() {
        fun onClickListener(str: String) {
            tv_type.text = str
            popView!!.dismiss()
        }
        if (popView == null) {
            val view = layoutInflater.inflate(R.layout.fragment_irregularities_pop, null)
            view.findViewById<AppCompatTextView>(R.id.tv_a).setOnClickListener { onClickListener((it!! as AppCompatTextView).text.toString()) }
            view.findViewById<AppCompatTextView>(R.id.tv_b).setOnClickListener { onClickListener((it!! as AppCompatTextView).text.toString()) }
            view.findViewById<AppCompatTextView>(R.id.tv_c).setOnClickListener { onClickListener((it!! as AppCompatTextView).text.toString()) }
            view.findViewById<AppCompatTextView>(R.id.tv_d).setOnClickListener { onClickListener((it!! as AppCompatTextView).text.toString()) }
            view.findViewById<AppCompatTextView>(R.id.tv_e).setOnClickListener { onClickListener((it!! as AppCompatTextView).text.toString()) }
            view.findViewById<AppCompatTextView>(R.id.tv_f).setOnClickListener { onClickListener((it!! as AppCompatTextView).text.toString()) }
            popView = CarTypePopView(_mActivity, view, tv_type)


        }
        popView!!.showAsDropDown(tv_type, 0, 10)
    }

    override fun toFragmentBundle(): Bundle {
        val bundle = Bundle()
        bundle.putString("a", checkValNull(tv_license_plate))
        bundle.putString("b", checkValNull(tv_num))
        bundle.putString("c", carNature)
        bundle.putString("d", checkValNull(tv_type))
        return bundle
    }

    override fun checkValNull(view: View): String {
        when (view) {
            tv_license_plate -> if (TextUtils.isEmpty(tv_license_plate.text)) throw IllegalStateException("请填写车牌号码!") else return tv_license_plate.text.toString()
            tv_num -> if (TextUtils.isEmpty(tv_num.text)) throw IllegalStateException("请填写车架后6位!") else return tv_num.text.toString()
            tv_type -> if (TextUtils.isEmpty(tv_type.text)) throw IllegalStateException("请选择车辆类型!") else return tv_type.text.toString()
        }
        return ""
    }

}