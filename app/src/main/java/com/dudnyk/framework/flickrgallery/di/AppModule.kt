package com.dudnyk.framework.flickrgallery.di

import com.dudnyk.framework.flickrgallery.common.Constants.BASE_URL
import com.dudnyk.framework.flickrgallery.data.remote.FlickrApi
import com.dudnyk.framework.flickrgallery.data.repository.RepositoryImpl
import com.dudnyk.framework.flickrgallery.domain.repository.Repository
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideFlickrApi(): FlickrApi = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(FlickrApi::class.java)

    @Provides
    @Singleton
    fun provideRepository(api: FlickrApi): Repository = RepositoryImpl(api)
}