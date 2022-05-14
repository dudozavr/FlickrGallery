package com.dudnyk.framework.flickrgallery.di

import android.content.Context
import androidx.room.Room
import com.dudnyk.framework.flickrgallery.common.Constants.BASE_URL
import com.dudnyk.framework.flickrgallery.common.Constants.DATABASE_NAME
import com.dudnyk.framework.flickrgallery.data.local.FlickrDataBase
import com.dudnyk.framework.flickrgallery.data.local.dao.PublicFeedTagDao
import com.dudnyk.framework.flickrgallery.data.remote.FlickrApi
import com.dudnyk.framework.flickrgallery.data.repository.public_feed.PublicFeedLocalRepositoryImpl
import com.dudnyk.framework.flickrgallery.data.repository.public_feed.PublicFeedRemoteRepositoryImpl
import com.dudnyk.framework.flickrgallery.data.repository.search.SearchRemoteRepositoryImpl
import com.dudnyk.framework.flickrgallery.domain.repository.public_feed.PublicFeedLocalRepository
import com.dudnyk.framework.flickrgallery.domain.repository.public_feed.PublicFeedRemoteRepository
import com.dudnyk.framework.flickrgallery.domain.repository.search.SearchRemoteRepository
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideFlickrApi(): FlickrApi =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(FlickrApi::class.java)

    @Provides
    @Singleton
    fun provideFlickrDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, FlickrDataBase::class.java, DATABASE_NAME).build()

    @Provides
    @Singleton
    fun providePublicFeedTagDao(dataBase: FlickrDataBase) = dataBase.getPublicFeedTagDao()

    @Provides
    @Singleton
    fun provideSearchRemoteRepository(api: FlickrApi): SearchRemoteRepository =
        SearchRemoteRepositoryImpl(api)

    @Provides
    @Singleton
    fun providePublicFeedRemoteRepository(api: FlickrApi): PublicFeedRemoteRepository =
        PublicFeedRemoteRepositoryImpl(api)

    @Provides
    @Singleton
    fun providePublicFeedLocalRepository(publicFeedTagDao: PublicFeedTagDao): PublicFeedLocalRepository =
        PublicFeedLocalRepositoryImpl(publicFeedTagDao)
}