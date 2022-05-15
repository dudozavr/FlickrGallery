package com.dudnyk.framework.flickrgallery.presentation.ui.search.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.dudnyk.framework.flickrgallery.domain.use_case.search.GetGroupsByQueryKeyUseCase

class SearchViewModel(
    private val getGroupByQueryKeyUseCase: GetGroupsByQueryKeyUseCase
) : ViewModel() {

    var pagePhotoGroupState = getPagingPhotoGroupInStateFlow("")

    fun getGroupByQueryKey(queryKey: String) {
        pagePhotoGroupState = getPagingPhotoGroupInStateFlow(queryKey)
    }

    private fun getPagingPhotoGroupInStateFlow(queryKey: String) =
        getGroupByQueryKeyUseCase(queryKey).cachedIn(viewModelScope)
}