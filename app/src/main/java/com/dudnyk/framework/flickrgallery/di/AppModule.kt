package com.dudnyk.framework.flickrgallery.di

import com.dudnyk.framework.flickrgallery.presentation.ui.home.viewmodel.HomeViewModel
import com.dudnyk.framework.flickrgallery.presentation.ui.photos_from_group.viewmodel.PhotosFromGroupViewModel
import com.dudnyk.framework.flickrgallery.presentation.ui.search.viewmodel.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel {
        HomeViewModel(
            getPhotosFromPublicFeedByTagsUseCase = get(),
            getPublicFeedTagsFromDBUseCase = get(),
            insertPublicFeedTagToBDUseCase = get(),
            deletePublicFeedTagByTagUseCase = get(),
            editPublicFeedTegUseCase = get()
        )
    }

    viewModel {
        PhotosFromGroupViewModel(
            getPhotosByGroupIdUseCase = get()
        )
    }

    viewModel {
        SearchViewModel(
            getGroupByQueryKeyUseCase = get()
        )
    }
}