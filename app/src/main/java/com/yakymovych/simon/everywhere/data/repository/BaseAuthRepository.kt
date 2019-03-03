package com.yakymovych.simon.everywhere.data.repository

import com.yakymovych.simon.everywhere.di.AuthInterceptor

abstract class BaseAuthRepository(private val authInterceptor: AuthInterceptor) : BaseRepository() {
    var token: String = ""
        set(value) {
            authInterceptor.sessionToken = value
            field = value
        }
}