package com.dudnyk.framework.flickrgallery.di

import androidx.room.Room
import com.dudnyk.framework.flickrgallery.common.Constants.BASE_URL
import com.dudnyk.framework.flickrgallery.common.Constants.DATABASE_NAME
import com.dudnyk.framework.flickrgallery.data.local.FlickrDataBase
import com.dudnyk.framework.flickrgallery.data.remote.FlickrApi
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val dataModule = module {

    single<FlickrApi> {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(FlickrApi::class.java)
    }

    single {
        Room.databaseBuilder(get(), FlickrDataBase::class.java, DATABASE_NAME).build()
    }

    single {
        get<FlickrDataBase>().getPublicFeedTagDao()
    }
}