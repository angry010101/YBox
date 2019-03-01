package com.yakymovych.simon.everywhere.data.responses

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class LoginOrRegisterResponse{

    @Expose
    @SerializedName("bLogin")
    var loginResult: Int? = 0

    @Expose
    @SerializedName("azToken")
    var token: String? = null

    @Expose
    @SerializedName("stError")
    var error: Error? = null

}