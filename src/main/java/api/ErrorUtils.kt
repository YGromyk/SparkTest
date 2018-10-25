package main.api

import com.google.gson.annotations.SerializedName
import spark.Response
import java.lang.Exception

object ErrorUtils {
    fun handleError(response: Response, exception: Exception) {
        println("${exception.message}")
        when (exception) {
            is NoSuchElementException -> {
                response.status(404)
                response.body(errorMessage(exception.message ?: "error occurs"))
            }
            else -> {
                response.status(400)
                response.body(errorMessage(exception.message ?: "error occurs"))
            }
        }
    }

    private fun errorMessage(cause: String): String {
        return GSON.toJson(ErrorMessage(cause))
    }
}

data class ErrorMessage(
        @SerializedName("message") var message: String
)