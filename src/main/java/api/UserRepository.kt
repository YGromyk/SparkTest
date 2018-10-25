package main.api

object UserRepository {
    private val users = mutableListOf(
            User("user1", "First user", 18),
            User("user2", "Second user", 18),
            User("user3", "Third user", 18),
            User("user4", "Fourth user", 18)
    )

    fun findUserByName(userName: String): User = users.find { it.username == userName }
            ?: throw NoSuchElementException("$userName does not exist")

    fun createUser(user: User) {
        if (users.any { it.username == user.username }) throw IllegalArgumentException("${user.username} already exists")
        users.add(user)
    }

    fun getAllUsers() = users
}