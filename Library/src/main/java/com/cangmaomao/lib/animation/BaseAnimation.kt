package com.cangmaomao.lib.animation

import android.animation.Animator
import android.view.View

interface BaseAnimation {

    fun getAnimatorsRight(view: View): Array<Animator>

    fun getAnimatorsLeft(view: View): Array<Animator>
}