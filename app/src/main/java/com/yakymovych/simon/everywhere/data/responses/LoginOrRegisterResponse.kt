package com.yakymovych.simon.everywhere.data.responses

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class LoginOrRegisterResponse{

    @Expose
    @SerializedName("token")
    var token: String? = ""

}