package com.example.threadapp

import android.os.Build
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.core.view.forEach
import com.example.core.base.BaseActivity
import com.example.threadapp.databinding.ActivityFirstHandlerBinding
import kotlin.random.Random

class FirstHandlerActivity : BaseActivity<ActivityFirstHandlerBinding>(
ActivityFirstHandlerBinding::inflate
) {

    private val handler = Handler(Looper.getMainLooper())

    private val token = Any()

    override fun setupUI() {
        initBtn()
    }

    private fun initBtn() {
        requireBinding().btnGroup.forEach {
            if (it is Button) it.setOnClickListener(universalBtnListener)
        }
    }

    private val nextRandomColorRunnable = Runnable {
        nextRandomColor()
    }

    private val toggleTestBtnStateRunnable = Runnable {
        toggleTestBtnState()
    }

    private val universalBtnListener = View.OnClickListener {
        Thread {
            when (it.id) {
                R.id.btn_enable_disable -> {
                    handler.post { toggleTestBtnState() }
                }
                R.id.btn_random_color -> {
                    handler.post { nextRandomColor() }
                }
                R.id.btn_enable_disable_delay -> {
                    handler.postDelayed(toggleTestBtnStateRunnable, DELAY)
                }
                R.id.btn_random_color_delay -> {
                    handler.postDelayed(nextRandomColorRunnable, DELAY)
                }
                R.id.btn_cancel_delay -> {
                    handler.removeCallbacks(toggleTestBtnStateRunnable)
                    handler.removeCallbacks(nextRandomColorRunnable)
                }
                R.id.btn_random_color_token -> {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                        handler.postDelayed({ nextRandomColor() }, token, DELAY)
                    }
                }
                R.id.btn_show_toast -> {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                        handler.postDelayed({ showToast() }, token, DELAY)
                    }
                }
                R.id.btn_cancel -> {
                    handler.removeCallbacksAndMessages(token)
                }
            }
        }.start()
    }

    private fun showToast() {
        Toast.makeText(this, getString(R.string.hello), Toast.LENGTH_SHORT).show()
    }

    private fun nextRandomColor() {
        val randomColor = -Random.nextInt(255 * 255 * 255)
        requireBinding().colorView.setBackgroundColor(randomColor)
    }

    private fun toggleTestBtnState() {
        requireBinding().btnTest.isEnabled = !requireBinding().btnTest.isEnabled
    }

    private companion object {
        @JvmStatic
        private val DELAY = 2000L
    }

}