package com.xuliucar.me.ui.pop

import android.app.Activity
import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.view.WindowManager
import android.widget.PopupWindow
import com.cangmaomao.lib.utils.width

class CarTypePopView(context: Context, view: View, view1: View)
    : PopupWindow(view, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT) {


    init {
        //设置view
        contentView = view

        val c = (context as Activity)

        width = view1.width

        height = WindowManager.LayoutParams.WRAP_CONTENT


        //设置背景（消失问题）
        setBackgroundDrawable(ColorDrawable())

        //设置可获取焦点
        isFocusable = true

        //设置点击外部可消失
        isOutsideTouchable = true

        //设置可触摸
        isTouchable = true

        //处理底部虚拟按键
        softInputMode = WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE


        //外部消失的方法
        setTouchInterceptor { _, _ -> false }

        //显示在点击的View下方
        showAsDropDown(view1,0,10)
    }

}

