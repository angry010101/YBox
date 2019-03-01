package com.yakymovych.simon.everywhere.data

import com.yakymovych.simon.everywhere.data.requests.LoginOrRegisterRequest
import com.yakymovych.simon.everywhere.data.responses.LoginOrRegisterResponse
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface RetroService {
    companion object {
        const val LOGIN_URL = "auth"
        const val CONTEXTS_URL = "JCOSSContextsGet"
        const val MENU_URL = "JCOSSHDAMenuGet"
        const val PAGE_URL = "JCOSSHDAPageGet"
        const val CASE_START_URL = "JCOSSHDACaseStart"
        const val CASE_SET_URL = "JCOSSHDACaseSet"
        const val CASE_GET_URL = "JCOSSHDACaseGet"
        const val ITEMS_GET_URL = "JCOSSHDAItemsGet"
        const val CASE_ITEMS_GET_URL = "JCOSSHDACaseItemsGet"
        const val CASE_FILE_GET_URL = "JCOSSHDAFileGet"
        const val LOGOUT_URL = "JCOSSClientLogout"

        const val BILLS_GET = "JCOSSCustomerBillGet"
        const val CONSUMPTION_GET = "JCOSSCustomerConsumptionGet"
        const val SUBSCRIPTION_GET = "JCOSSCustomerSubscriptionGet"
    }

    @Headers("Content-Type: application/json")
    @POST(LOGIN_URL)
    fun login(@Body loginRequest: LoginOrRegisterRequest): Single<LoginOrRegisterResponse>
}