package com.dudnyk.framework.flickrgallery.app

import android.app.Application
import com.dudnyk.framework.flickrgallery.di.appModule
import com.dudnyk.framework.flickrgallery.di.dataModule
import com.dudnyk.framework.flickrgallery.di.repositoryModule
import com.dudnyk.framework.flickrgallery.di.useCasesModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class FlickrGalleryApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@FlickrGalleryApp)
            modules(
                appModule,
                dataModule,
                repositoryModule,
                useCasesModule
            )
        }
    }
}