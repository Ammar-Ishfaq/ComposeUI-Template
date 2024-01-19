package com.m.ammar.composeTemplate.data

import android.util.Log
import com.google.gson.Gson
import com.m.ammar.composeTemplate.utility.Constants.API_BASE_URL
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.patch
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.statement.bodyAsText
import javax.inject.Inject
import kotlin.coroutines.cancellation.CancellationException


abstract class BaseApiService(
    protected val httpClient: HttpClient,
    protected val baseUrl: String,
) {
    protected suspend inline fun <reified T> get(
        endpoint: String,
        block: HttpRequestBuilder.() -> Unit = {}
    ): T {
        return httpClient.get(urlString = baseUrl + endpoint, block = block).body()
    }

    protected suspend inline fun <reified T> post(
        endpoint: String,
        block: HttpRequestBuilder.() -> Unit
    ): T {
        return httpClient.post(urlString = baseUrl + endpoint, block = block).body()
    }

    protected suspend inline fun <reified T> put(
        endpoint: String,
        block: HttpRequestBuilder.() -> Unit = {}
    ): T {
        return httpClient.put(urlString = baseUrl + endpoint, block = block).body()
    }

    protected suspend inline fun <reified T> patch(
        endpoint: String,
        block: HttpRequestBuilder.() -> Unit
    ): T {
        return httpClient.patch(urlString = baseUrl + endpoint, block = block).body()
    }

    protected suspend inline fun <reified T> delete(
        endpoint: String,
        block: HttpRequestBuilder.() -> Unit = {}
    ): T {
        return httpClient.delete(urlString = baseUrl + endpoint, block = block).body()
    }
}

class ApiInterface @Inject constructor(val client: HttpClient) :
    BaseApiService(client, API_BASE_URL) {
    suspend fun getList(): String {
        return try {
            val mRes = client.get(API_BASE_URL + "ResistanceGuide.json").bodyAsText()

            val gson = Gson()
            val json = gson.toJson(mRes)
            Log.d("FARAN", json[0].toString()[0].toString())

            mRes
        } catch (e: Exception) {
            if (e is CancellationException) throw e
            e.printStackTrace()
            ""
        }
    }
//    = get(endpoint = "ResistanceGuide.json") {
//        contentType(ContentType.Application.Json)
//
//    }
}
