package com.example.source

import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor



fun main() {
    val loggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)


    val client = OkHttpClient.Builder().addInterceptor(loggingInterceptor)
        .build()


    val request =
        Request.Builder().get().url("https://www.anapioficeandfire.com/api/houses").build()

    val call = client.newCall(request)
    val response = call.execute()
    if (response.isSuccessful) {
        val responseBodyString = response.body.toString()
        println(responseBodyString)
    }
}