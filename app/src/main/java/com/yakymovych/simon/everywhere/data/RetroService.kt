package com.yakymovych.simon.everywhere.data

import com.yakymovych.simon.everywhere.data.requests.LoginOrRegisterRequest
import com.yakymovych.simon.everywhere.data.responses.LoginOrRegisterResponse
import com.yakymovych.simon.everywhere.data.responses.GetTasksResponse
import io.reactivex.Single
import retrofit2.http.*

interface RetroService {
    companion object {
        const val LOGIN_URL = "auth"
        const val GET_TASKS_URL = "tasks"
    }

    @Headers("Content-Type: application/json")
    @POST(LOGIN_URL)
    fun login(@Body loginRequest: LoginOrRegisterRequest): Single<LoginOrRegisterResponse>

    @GET(GET_TASKS_URL)
//    Call<List<Repo>> listRepos(@Path("user") String user);
    fun getTasks(@Query("page") page: Int, @Query("sort") sort: String): Single<GetTasksResponse>

}