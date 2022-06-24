package com.junior.cinephile.di

import com.google.gson.GsonBuilder
import com.junior.cinephile.data.network.Api
import com.junior.cinephile.data.network.HttpRoutes
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit =
        Retrofit.Builder().baseUrl(HttpRoutes.BASE_URL).client(client)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient().newBuilder().build()


    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit): Api = retrofit.create(Api::class.java)
}

