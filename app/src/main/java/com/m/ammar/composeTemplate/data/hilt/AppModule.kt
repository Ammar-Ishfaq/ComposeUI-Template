package com.m.ammar.composeTemplate.data.hilt

import android.app.Application
import android.content.Context
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
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
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



    @Provides
    @Singleton
    fun provideApiClient(): HttpClient {
        val json = Json { ignoreUnknownKeys = true }
      return HttpClient {
            install(ContentNegotiation) {
                json(json, contentType = ContentType.Any)
            }
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