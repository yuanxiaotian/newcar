package com.cangmaomao.lib.animation;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.cangmaomao.lib.utils.DisplayUtilsKt;

import org.jetbrains.annotations.NotNull;

public class SlideInRightAnimation implements BaseAnimation {


    @NotNull
    @Override
    public Animator[] getAnimatorsRight(@NotNull View view) {
        int w = DisplayUtilsKt.width();
        return new Animator[]{
                ObjectAnimator.ofFloat(view, "translationX", 1080, 0)
        };
    }

    @NotNull
    @Override
    public Animator[] getAnimatorsLeft(@NotNull View view) {
        int w = DisplayUtilsKt.width();
        return new Animator[]{
                ObjectAnimator.ofFloat(view, "translationX", -1080, 0)
        };
    }
}
