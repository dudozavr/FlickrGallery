package com.dudnyk.framework.flickrgallery.di

import com.dudnyk.framework.flickrgallery.domain.use_case.public_feed.local.DeletePublicFeedTagByTagFromBDUseCase
import com.dudnyk.framework.flickrgallery.domain.use_case.public_feed.local.EditPublicFeedTegUseCase
import com.dudnyk.framework.flickrgallery.domain.use_case.public_feed.local.GetPublicFeedTagsFromDBUseCase
import com.dudnyk.framework.flickrgallery.domain.use_case.public_feed.local.InsertPublicFeedTagToBDUseCase
import com.dudnyk.framework.flickrgallery.domain.use_case.public_feed.remote.GetPhotosFromPublicFeedByTagsUseCase
import com.dudnyk.framework.flickrgallery.domain.use_case.search.GetGroupsByQueryKeyUseCase
import com.dudnyk.framework.flickrgallery.domain.use_case.search.GetPhotosByGroupIdUseCase
import org.koin.dsl.module

val useCasesModule = module {

    factory {
        DeletePublicFeedTagByTagFromBDUseCase(repository = get())
    }

    factory {
        EditPublicFeedTegUseCase(repository = get())
    }

    factory {
        GetPublicFeedTagsFromDBUseCase(repository = get())
    }

    factory {
        InsertPublicFeedTagToBDUseCase(repository = get())
    }

    factory {
        GetPhotosFromPublicFeedByTagsUseCase(repository = get())
    }

    factory {
        GetGroupsByQueryKeyUseCase(repository = get())
    }

    factory {
        GetPhotosByGroupIdUseCase(repository = get())
    }
}