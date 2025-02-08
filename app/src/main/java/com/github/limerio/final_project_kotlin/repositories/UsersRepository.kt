package com.github.limerio.final_project_kotlin.repositories

import com.github.limerio.final_project_kotlin.models.User
import com.github.limerio.final_project_kotlin.utils.Http
import com.github.limerio.final_project_kotlin.utils.USERS_BASE_URL
import kotlinx.serialization.json.Json

object UsersRepository {
    val json = Json { ignoreUnknownKeys = true }

    fun findAll(): List<User> {
        val data = Http.get(USERS_BASE_URL)
        return json.decodeFromString(data)
    }

    fun findById(id: Int): User {
        val data = Http.get("$USERS_BASE_URL/$id")
        return json.decodeFromString(data)
    }
}