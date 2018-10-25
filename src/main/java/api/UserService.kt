package main.api

import main.Main
import spark.Spark

object UserService {
    @JvmStatic
    val USER_PATH = "/user"
    @JvmStatic
    val USERS_PATH = "/users"
    @JvmStatic
    val USERNAME_PARAMETER = ":username"
    @JvmStatic
    val USER_PARAM_PATH = "$USERS_PATH/$USERNAME_PARAMETER"

    @JvmStatic
    fun initUserService() {
        Spark.post(USER_PATH) { req, res ->
            try {
                val user = Main.GSON.fromJson(req.body(), User::class.java)
                UserRepository.createUser(user)
                SuccessResponse.successResponse(res)
            } catch (e: IllegalArgumentException) {
                ErrorUtils.handleError(res, e)
            }
            res.body()
        }
        Spark.get(USER_PARAM_PATH) { request, response ->
            try {
                val user = UserRepository.findUserByName(request.params(USERNAME_PARAMETER))
                response.body(Main.GSON.toJson(user))
                response.status(200)
            } catch (exception: NoSuchElementException) {
                ErrorUtils.handleError(response, exception)
            }
            response.body().toString()
        }
        Spark.get(USERS_PATH) { _, res ->
            SuccessResponse.successResponse(res)
            Main.GSON.toJson(UserRepository.getAllUsers())
        }
    }
}