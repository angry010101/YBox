package com.yakymovych.simon.everywhere.di

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class AuthInterceptor @Inject constructor(var sessionToken: String) : Interceptor {


    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        val requestBuilder = request.newBuilder()

        requestBuilder.addHeader("Authorization", "Bearer " + sessionToken)

        return chain.proceed(requestBuilder.build())
    }
}