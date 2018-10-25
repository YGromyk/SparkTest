package main

import com.google.gson.Gson
import spark.Spark
import spark.Spark.*
import spark.servlet.SparkApplication
import java.rmi.ServerError

object Main {
    @JvmStatic
    val GSON = Gson()

    @JvmStatic
    fun main(args: Array<String>) {
        setPort(8080)
        post("/user") { req, res ->
            return@post {
                req.queryParamsValues("user").forEach { println(it) }
                res.status(200)
                res.type("application/json")
                res.body().toString()
            }.invoke()
        }
        get("/user/:username") { req, res ->
            return@get userList.find { it.username == req.params(":username") }
                    ?.let {
                        println("received new get")
                        val user = "${GSON.toJson(it)}\n"
                        res.type("text/json")
                        res.status(200)
                        res.body(user)
                        res.body().toString()
                    } ?: {
                res.status(404)
                res.body()
            }.invoke()
        }
    }
}

val userList = mutableListOf(
        User("user1", "First user", 18),
        User("user2", "Second user", 18),
        User("user3", "Third user", 18),
        User("user4", "Fourth user", 18)
)

data class User(var username: String, var name: String, var age: Int)

fun initServer() {
    threadPool(3)
    setPort(8080)
}