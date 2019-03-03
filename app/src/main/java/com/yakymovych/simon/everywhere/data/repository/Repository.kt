package com.yakymovych.simon.everywhere.data.repository

import com.yakymovych.simon.everywhere.data.RetroService
import com.yakymovych.simon.everywhere.data.requests.LoginOrRegisterRequest
import com.yakymovych.simon.everywhere.data.responses.LoginOrRegisterResponse
import com.yakymovych.simon.everywhere.data.responses.GetTasksResponse
import com.yakymovych.simon.everywhere.di.AuthInterceptor
import com.yakymovych.simon.everywhere.utils.SchedulerProvider
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class Repository @Inject constructor(val retroService: RetroService,
                                     val schedulerProvider: SchedulerProvider,
                                     val authInterceptor: AuthInterceptor) : BaseAuthRepository(authInterceptor){


    fun login(email: String, pass: String, doRegister: Boolean): Single<LoginOrRegisterResponse>{
        return retroService.login(LoginOrRegisterRequest(email,pass,doRegister))
                .doAfterSuccess { token = it.token ?: token }
                .compose(schedulerProvider.getSchedulersForSingle())
    }


}