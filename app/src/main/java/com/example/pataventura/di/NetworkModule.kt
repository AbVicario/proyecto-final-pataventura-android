package com.example.pataventura.di

import com.example.pataventura.data.network.ApiClient
import com.example.pataventura.data.network.ApiMascota
import com.example.pataventura.data.network.ApiServicio
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl("http://10.0.2.2:8000")
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    @Singleton
    @Provides
    fun provideApiClient(retrofit: Retrofit): ApiClient {
        return retrofit.create(ApiClient::class.java)
    }

    @Singleton
    @Provides
    fun provideApiMascota(retrofit: Retrofit): ApiMascota {
        return retrofit.create(ApiMascota::class.java)
    }

    @Singleton
    @Provides
    fun provideApiServicio(retrofit: Retrofit): ApiServicio {
        return retrofit.create(ApiServicio::class.java)
    }

}