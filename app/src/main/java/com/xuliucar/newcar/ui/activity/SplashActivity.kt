package com.xuliucar.newcar.ui.activity

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import com.cangmaomao.lib.action.a_main
import com.cangmaomao.lib.utils.SPUtils.create
import com.xuliucar.newcar.R

class SplashActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val isLogin = create("userInfo").get("isLogin", false) as Boolean
        Handler().postAtTime({
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(a_main)).putExtra("flag", if (isLogin) 110 else 111))
        }, 2500)
        finish()

    }
}