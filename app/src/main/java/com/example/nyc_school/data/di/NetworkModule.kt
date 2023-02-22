package com.example.nyc_school.data.di

import com.example.nyc_school.BuildConfig
import com.example.nyc_school.data.api_service.RetrofitNycSchoolNetworkApi
import com.example.nyc_school.data.api_service.result.ResultCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Call
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

private const val BASE_URL = "https://data.cityofnewyork.us/"

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun providesResultCallAdapterFactory(): ResultCallAdapterFactory = ResultCallAdapterFactory()

    @Provides
    @Singleton
    fun providesGsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    @Singleton
    fun providesOkHttpCallFactory(): Call.Factory = OkHttpClient.Builder()
        .addInterceptor(
            HttpLoggingInterceptor()
                .apply {
                    if (BuildConfig.DEBUG) {
//                        level = HttpLoggingInterceptor.Level.BODY
                        level = HttpLoggingInterceptor.Level.HEADERS
                    }
                },
        )
        .build()

    @Provides
    @Singleton
    fun providesRetrofitNycSchoolNetworkApi(
        okhttpCallFactory: Call.Factory,
        resultCallAdapterFactory: ResultCallAdapterFactory,
        gsonConverterFactory: GsonConverterFactory,
    ): RetrofitNycSchoolNetworkApi =
        Retrofit.Builder()
        .baseUrl(BASE_URL)
//        .client(client)
        .callFactory(okhttpCallFactory)
        .addCallAdapterFactory(resultCallAdapterFactory)
        .addConverterFactory(gsonConverterFactory)
        .build()
        .create(RetrofitNycSchoolNetworkApi::class.java)
}
