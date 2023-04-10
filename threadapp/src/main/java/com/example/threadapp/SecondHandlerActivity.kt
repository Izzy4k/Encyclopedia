package com.example.threadapp

import android.os.Build
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.core.view.forEach
import com.example.core.base.BaseActivity
import com.example.threadapp.databinding.ActivitySecondHandlerBinding
import kotlin.random.Random

class SecondHandlerActivity : BaseActivity<ActivitySecondHandlerBinding>(
    ActivitySecondHandlerBinding::inflate
) {
    private val handler = Handler(Looper.getMainLooper()) {
        when (it.what) {
            MSG_TOGGLE_BUTTON -> toggleTestBtnState()
            MSG_NEXT_RANDOM_COLOR -> nextRandomColor()
            MSG_SHOW_TOAST -> showToast()
        }
        return@Handler true
    }
    private val token = Any()

    override fun setupUI() {
        initBtn()
    }

    private fun initBtn() {
        requireBinding().btnGroup.forEach {
            if (it is Button) it.setOnClickListener(universalBtnListener)
        }
    }

    private val universalBtnListener = View.OnClickListener {
        Thread {
            when (it.id) {
                R.id.btn_enable_disable -> {
                    val message = handler.obtainMessage(MSG_TOGGLE_BUTTON)
                    handler.sendMessage(message)
                }
                R.id.btn_random_color -> {
                    val message = handler.obtainMessage(MSG_NEXT_RANDOM_COLOR)
                    handler.sendMessage(message)
                }
                R.id.btn_enable_disable_delay -> {
                    val message = Message()
                    message.what = MSG_TOGGLE_BUTTON
                    handler.sendMessageDelayed(message, DELAY)
                }
                R.id.btn_random_color_delay -> {
                    val messageNextColor: Message = Message.obtain(handler) {
                        nextRandomColor()
                    }
                    handler.sendMessageDelayed(messageNextColor, DELAY)
                }
                R.id.btn_random_color_token -> {
                    val message = Message.obtain(handler, MSG_NEXT_RANDOM_COLOR)
                    message.obj = token
                    handler.sendMessageDelayed(message, DELAY)
                }
                R.id.btn_show_toast -> {
                    val message = Message.obtain(handler, MSG_SHOW_TOAST).apply {
                        obj = token
                    }
                    handler.sendMessageDelayed(message, DELAY)
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
        private const val MSG_TOGGLE_BUTTON = 1
        private const val MSG_NEXT_RANDOM_COLOR = 2
        private const val MSG_SHOW_TOAST = 3
    }
}