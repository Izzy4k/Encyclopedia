package com.example.core.base

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<VB : ViewBinding>(
    private val binder: (LayoutInflater) -> VB
) : Activity(
) {

    private var binding: VB? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = binder.invoke(layoutInflater).also { setContentView(it.root) }
        setupUI()
        setupObserver()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    protected abstract fun setupUI()

    protected open fun setupObserver(){}

    protected fun requireBinding(): VB = checkNotNull(binding)
}