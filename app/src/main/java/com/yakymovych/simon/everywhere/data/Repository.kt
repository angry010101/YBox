package com.yakymovych.simon.everywhere.data

import com.yakymovych.simon.everywhere.data.requests.LoginOrRegisterRequest
import com.yakymovych.simon.everywhere.data.responses.LoginOrRegisterResponse
import com.yakymovych.simon.everywhere.utils.SchedulerProvider
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class Repository @Inject constructor(private val retroService: RetroService,private val schedulerProvider: SchedulerProvider){
    var token: String = ""

    fun login(email: String, pass: String, doRegister: Boolean): Single<LoginOrRegisterResponse>{
        return retroService.login(LoginOrRegisterRequest(email,pass,doRegister))
                .doAfterSuccess { token = it.token ?: token }
                .compose(schedulerProvider.getSchedulersForSingle())
    }


}