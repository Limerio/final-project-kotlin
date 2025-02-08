package com.github.limerio.final_project_kotlin.utils

import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody


object Http {
    private val client = OkHttpClient()

    fun get(url: String): String {
        val request = Request.Builder().url(url).build()
        return client.newCall(request).execute().use {
            if (!it.isSuccessful) {
                throw Exception("Réponse du serveur incorrect :${it.code}\n${it.body.string()}")
            }
            it.body.string()
        }
    }

    fun delete(url: String): String {
        val request = Request.Builder().url(url).build()
        return client.newCall(request).execute().use {
            if (!it.isSuccessful) {
                throw Exception("Réponse du serveur incorrect :${it.code}\n${it.body.string()}")
            }
            it.body.string()
        }
    }

   fun post(url: String, body: RequestBody): String {
       val request = Request.Builder().post(body).url(url).build()
       return client.newCall(request).execute().use {
           if (!it.isSuccessful) {
               throw Exception("Réponse du serveur incorrect :${it.code}\n${it.body.string()}")
           }
           it.body.string()
       }
   }


}
