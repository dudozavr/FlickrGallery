package com.dudnyk.framework.flickrgallery.di

import com.dudnyk.framework.flickrgallery.data.repository.public_feed.PublicFeedLocalRepositoryImpl
import com.dudnyk.framework.flickrgallery.data.repository.public_feed.PublicFeedRemoteRepositoryImpl
import com.dudnyk.framework.flickrgallery.data.repository.search.SearchRemoteRepositoryImpl
import com.dudnyk.framework.flickrgallery.domain.repository.public_feed.PublicFeedLocalRepository
import com.dudnyk.framework.flickrgallery.domain.repository.public_feed.PublicFeedRemoteRepository
import com.dudnyk.framework.flickrgallery.domain.repository.search.SearchRemoteRepository
import org.koin.dsl.module

val repositoryModule = module {

    single<PublicFeedLocalRepository> {
        PublicFeedLocalRepositoryImpl(publicFeedTagDao = get())
    }

    single<PublicFeedRemoteRepository> {
        PublicFeedRemoteRepositoryImpl(api = get())
    }

    single<SearchRemoteRepository> {
        SearchRemoteRepositoryImpl(api = get())
    }
}