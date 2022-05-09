package com.dudnyk.framework.flickrgallery.presentation.ui.search.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.dudnyk.framework.flickrgallery.domain.use_case.search.GetGroupsByQueryKeyUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getGroupByQueryKeyUseCase: GetGroupsByQueryKeyUseCase
) : ViewModel() {

    var pagePhotoGroupState = getPagingPhotoGroupInStateFlow("")

    fun getGroupByQueryKey(queryKey: String) {
        pagePhotoGroupState = getPagingPhotoGroupInStateFlow(queryKey)
    }

    private fun getPagingPhotoGroupInStateFlow(queryKey: String) =
        getGroupByQueryKeyUseCase(queryKey).cachedIn(viewModelScope)
}