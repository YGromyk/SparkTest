package main

import com.google.gson.Gson
import main.api.*
import spark.Spark.*

object Main {
    @JvmStatic
    val GSON = Gson()

    @JvmStatic
    fun main(args: Array<String>) {
        initServer()
        UserService.initUserService()
    }

    fun initServer() {
        setPort(8080)
        before { request, response ->
            println(request.body())
            response.type(CONTENT_TYPE)
        }
    }
}
