package com.m.ammar.composeTemplate.data.hilt

import android.app.Application
import android.content.Context
import com.google.gson.Gson
import com.m.ammar.composeTemplate.data.managers.DataManager
import com.m.ammar.composeTemplate.data.managers.DataManagerImpl
import com.m.ammar.composeTemplate.prefs.PreferencesHelper
import com.m.ammar.composeTemplate.prefs.PreferencesHelperImpl
import com.m.ammar.composeTemplate.utility.Constants.PREF_NAME
import com.m.ammar.composeTemplate.utility.ResourceProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.http.ContentType
import kotlinx.serialization.json.Json
import okhttp3.ConnectionSpec
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    @Singleton
    fun okHttp(): OkHttpClient {
        val tlsSpecs = listOf(ConnectionSpec.MODERN_TLS, ConnectionSpec.CLEARTEXT)
        return OkHttpClient.Builder().connectionSpecs(tlsSpecs)
            .connectTimeout(100, TimeUnit.SECONDS).readTimeout(100, TimeUnit.SECONDS)
            .writeTimeout(1000, TimeUnit.SECONDS).build()
    }

//    val client = HttpClient {
//        install(JsonFeature) {
//            serializer = KotlinxSerializer(Json {
//                ignoreUnknownKeys = true
//            })
//        }
//    }

    @Provides
    @Singleton
    fun provideApiClient() = HttpClient(Android) {
        install(Logging) {
            logger = object : io.ktor.client.plugins.logging.Logger {
                override fun log(message: String) {
                    println("KTOR_logging => $message")
                }
            }
            level = LogLevel.ALL
        }
        val json = Json { ignoreUnknownKeys = true }
        HttpClient {
            install(ContentNegotiation) {
                Gson()
            }
        }
//        val json = Json {
//            ignoreUnknownKeys = true
//            explicitNulls = false
//
//
//            prettyPrint = true
//            isLenient = true
//            ignoreUnknownKeys = true
//
//        }
//
//        install(ContentNegotiation) {
//            json(json, contentType = ContentType.Application.Any)
//
//        }
//
//
//        // Set default headers for every request
        defaultRequest {
            header("Content-Type", "text/plain; charset=utf-8")
            header("Accept", "*/*")

        }

    }

    @Singleton
    @Provides
    fun providePreferenceHelper(preferencesHelperImpl: PreferencesHelperImpl): PreferencesHelper {
        return preferencesHelperImpl
    }

    @Singleton
    @Provides
    fun provideDataManager(dataManagerImpl: DataManagerImpl): DataManager {
        return dataManagerImpl
    }

    @Provides
    fun provideResourceProvider(context: Context): ResourceProvider {
        return ResourceProvider(context.applicationContext)
    }

    @Provides
    @Singleton
    fun provideContext(application: Application): Context {
        return application
    }

    @Provides
    fun providePreferenceName(): String {
        return PREF_NAME
    }


}