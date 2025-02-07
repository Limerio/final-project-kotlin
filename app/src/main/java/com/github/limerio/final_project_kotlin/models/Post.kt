package com.github.limerio.final_project_kotlin.models

import kotlinx.serialization.Serializable

@Serializable
data class Post(val id: Int, val title: String, val body: String, val userId: Int)
