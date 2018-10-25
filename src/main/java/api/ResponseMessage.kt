package main.api

import com.google.gson.annotations.SerializedName

data class ResponseMessage (
        @SerializedName("message") var message: String
)