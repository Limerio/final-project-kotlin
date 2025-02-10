package com.github.limerio.final_project_kotlin.utils

import android.os.NetworkOnMainThreadException
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody


object Http {
    private val client = OkHttpClient()

    fun get(url: String): String {
        val request = Request.Builder().url(url).build()
        return try {
            client.newCall(request).execute().use {
                if (!it.isSuccessful) {
                    throw Exception("Réponse du serveur incorrect :${it.code}\n${it.body.string()}")
                }
                it.body.string()
            }
        } catch (e: NetworkOnMainThreadException) {
            println(e.cause)

            return e.message ?: ""
        }
    }

    fun delete(url: String): String {
        val request = Request.Builder().url(url).build()
        return try {
            client.newCall(request).execute().use {
                if (!it.isSuccessful) {
                    throw Exception("Réponse du serveur incorrect :${it.code}\n${it.body.string()}")
                }
                it.body.string()
            }
        } catch (e: NetworkOnMainThreadException) {
            throw Exception("Réponse du serveur incorrect :${e.message}")
        }
    }

    fun post(url: String, body: RequestBody): String {
        val request = Request.Builder().post(body).url(url).build()
        return try {
            client.newCall(request).execute().use {
                if (!it.isSuccessful) {
                    throw Exception("Réponse du serveur incorrect :${it.code}\n${it.body.string()}")
                }
                it.body.string()
            }
        } catch (e: NetworkOnMainThreadException) {
            throw Exception("Réponse du serveur incorrect :${e.message}")
        }
    }


}
