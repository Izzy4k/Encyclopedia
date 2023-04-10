package com.example.threadapp

import android.content.Intent
import com.example.core.base.BaseActivity
import com.example.threadapp.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>(
    ActivityMainBinding::inflate
) {

    override fun setupUI() {
        initBtn()
    }

    private fun initBtn() {
        requireBinding().btnFirst.setOnClickListener {
            openFirstHandlerActivity()
        }
        requireBinding().btnSecond.setOnClickListener {
            openSecondHandlerActivity()
        }
    }

    private fun openSecondHandlerActivity() {
        startActivity(Intent(this, SecondHandlerActivity::class.java))
    }

    private fun openFirstHandlerActivity() {
        startActivity(Intent(this, FirstHandlerActivity::class.java))
    }


}