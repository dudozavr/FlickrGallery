package com.dudnyk.framework.flickrgallery.presentation.ui.photos_from_group.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.dudnyk.framework.flickrgallery.domain.use_case.search.GetPhotosByGroupIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PhotosFromGroupViewModel @Inject constructor(
    private val getPhotosByGroupIdUseCase: GetPhotosByGroupIdUseCase
) : ViewModel() {

    var pagePhotoState = getPhotosByGroupIdUseCase("")

    fun getPhotosByGroupId(groupId: String) {
        pagePhotoState = getPhotosByGroupIdInStateFlow(groupId)
    }

    private fun getPhotosByGroupIdInStateFlow(groupId: String) =
        getPhotosByGroupIdUseCase(groupId).cachedIn(viewModelScope)
}