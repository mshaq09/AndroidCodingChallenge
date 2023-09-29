package com.thermondo.androidchallenge.service

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var req = chain.request()

        return chain.proceed(req)
    }
}