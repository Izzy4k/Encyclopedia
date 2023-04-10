package com.example.source.base

open class BaseOkHttpSource(
    private val config: OkHttpConfig
) {

    val gson = config.gson
    val client = config.client
}