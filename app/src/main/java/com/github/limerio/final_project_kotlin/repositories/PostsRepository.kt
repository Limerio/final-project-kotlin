package com.github.limerio.final_project_kotlin.repositories

import com.github.limerio.final_project_kotlin.models.Post
import com.github.limerio.final_project_kotlin.utils.Http
import com.github.limerio.final_project_kotlin.utils.POSTS_BASE_URL
import kotlinx.serialization.json.Json

object PostsRepository {
    val json = Json { ignoreUnknownKeys = true }

    fun findAll(): List<Post> {
        val data = Http.get(POSTS_BASE_URL)
        return json.decodeFromString(data)
    }

    fun findById(id: Int): Post {
        val json = Http.get("$POSTS_BASE_URL/$id")
        return Http.gson.fromJson(json, Post::class.java)
    }

    fun delete(id: Int): Int {
        Http.delete("$POSTS_BASE_URL/$id")

        return id
    }
}