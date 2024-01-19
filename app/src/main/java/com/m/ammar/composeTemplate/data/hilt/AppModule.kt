package com.m.ammar.composeTemplate.data.hilt

import android.app.Application
import android.content.Context
import com.m.ammar.composeTemplate.data.RetrofitInterface
import com.m.ammar.composeTemplate.data.managers.DataManager
import com.m.ammar.composeTemplate.prefs.PreferencesHelper
import com.m.ammar.composeTemplate.prefs.PreferencesHelperImpl
import com.m.ammar.composeTemplate.utility.Constants.API_BASE_URL
import com.m.ammar.composeTemplate.utility.Constants.PREF_NAME
import com.m.ammar.composeTemplate.utility.ResourceProvider
import com.m.ammar.composeTemplate.data.managers.DataManagerImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.ConnectionSpec
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
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
    fun provideRetrofitInterface(client: OkHttpClient): RetrofitInterface {
        return Retrofit.Builder().baseUrl(API_BASE_URL).client(client)
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(RetrofitInterface::class.java)
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