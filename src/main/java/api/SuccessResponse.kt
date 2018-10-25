package main.api

import spark.Response


object SuccessResponse {
    fun successResponse(response: Response) {
        response.status(200)
        response.body(GSON.toJson(ResponseMessage("Success")))
    }
}