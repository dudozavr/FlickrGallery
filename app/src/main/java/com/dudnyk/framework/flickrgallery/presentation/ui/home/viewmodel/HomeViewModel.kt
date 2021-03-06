package com.dudnyk.framework.flickrgallery.presentation.ui.home.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dudnyk.framework.flickrgallery.common.Constants.DELAY_AFTER_DOWNLOADING_DATA_FROM_NETWORK
import com.dudnyk.framework.flickrgallery.common.Constants.PUBLIC_FEED_SAVE_STATE_KEY
import com.dudnyk.framework.flickrgallery.common.PublicFeedResult
import com.dudnyk.framework.flickrgallery.common.PublicFeedState
import com.dudnyk.framework.flickrgallery.common.SavableMutableSaveStateFlow
import com.dudnyk.framework.flickrgallery.common.extension.MutableListExtension.replaceByIndex
import com.dudnyk.framework.flickrgallery.common.extension.MutableListExtension.replaceByIndexOrAdd
import com.dudnyk.framework.flickrgallery.common.utils.ResultUtils.processResult
import com.dudnyk.framework.flickrgallery.domain.model.PublicFeedTag
import com.dudnyk.framework.flickrgallery.domain.use_case.public_feed.local.DeletePublicFeedTagByTagFromBDUseCase
import com.dudnyk.framework.flickrgallery.domain.use_case.public_feed.local.EditPublicFeedTegUseCase
import com.dudnyk.framework.flickrgallery.domain.use_case.public_feed.local.GetPublicFeedTagsFromDBUseCase
import com.dudnyk.framework.flickrgallery.domain.use_case.public_feed.local.InsertPublicFeedTagToBDUseCase
import com.dudnyk.framework.flickrgallery.domain.use_case.public_feed.remote.GetPhotosFromPublicFeedByTagsUseCase
import com.dudnyk.framework.flickrgallery.presentation.ui.home.ItemState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getPhotosFromPublicFeedByTagsUseCase: GetPhotosFromPublicFeedByTagsUseCase,
    private val getPublicFeedTagsFromDBUseCase: GetPublicFeedTagsFromDBUseCase,
    private val insertPublicFeedTagToBDUseCase: InsertPublicFeedTagToBDUseCase,
    private val deletePublicFeedTagByTagUseCase: DeletePublicFeedTagByTagFromBDUseCase,
    private val editPublicFeedTegUseCase: EditPublicFeedTegUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private var downloadedPublicFeeds = mutableListOf<PublicFeedState>()

    private val _publicFeedListState = SavableMutableSaveStateFlow(
        savedStateHandle,
        PUBLIC_FEED_SAVE_STATE_KEY,
        emptyList<PublicFeedState>()
    )
    val publicFeedListState = _publicFeedListState.asStateFlow()

    init {
        downloadedPublicFeeds = _publicFeedListState.value.toMutableList()
    }

    fun insertAndDisplayPublicFeedTag(publicFeedTag: PublicFeedTag) {
        viewModelScope.launch(Dispatchers.IO) {
            insertPublicFeedTagToBDUseCase(publicFeedTag)
            updatePublicFeedTagByTag(publicFeedTag)
        }
    }

    fun getPhotosFromPublicFeedByTags() {
        if (downloadedPublicFeeds.isEmpty()) {
            viewModelScope.launch(Dispatchers.IO) {
                getPublicFeeds(getPublicFeedTagsFromDBUseCase())
            }
        }
    }

    fun deletePublicFeedTagByTag(publicFeedTag: PublicFeedTag) {
        viewModelScope.launch(Dispatchers.IO) {
            processPublicFeedResponse(deletePublicFeedTagByTagUseCase(publicFeedTag))
        }
    }

    fun editPublicFeedTag(
        oldPublicFeedTag: PublicFeedTag,
        newPublicFeedTag: PublicFeedTag
    ) {
        processPublicFeedResponse(editPublicFeedTegUseCase(oldPublicFeedTag, newPublicFeedTag))
    }

    fun updatePublicFeedTagByTag(publicFeedTag: PublicFeedTag) {
        processPublicFeedResponse(getPhotosFromPublicFeedByTagsUseCase(publicFeedTag))
    }

    private fun getPublicFeeds(publicFeedTags: List<PublicFeedTag>) {
        viewModelScope.launch(Dispatchers.IO) {
            publicFeedTags.forEach { publicFeedTag ->
                processPublicFeedResponse(getPhotosFromPublicFeedByTagsUseCase(publicFeedTag))
                delay(DELAY_AFTER_DOWNLOADING_DATA_FROM_NETWORK)
            }
            processPublicFeedResponse(getPhotosFromPublicFeedByTagsUseCase())
        }
    }

    private fun processPublicFeedResponse(response: Flow<PublicFeedResult>) {
        response.onEach { publicFeedResource ->
            val indexById =
                downloadedPublicFeeds.indexOfFirst { it.id == publicFeedResource.id }
            processResult(
                publicFeedResource,
                onLoading = { id ->
                    downloadedPublicFeeds.replaceByIndexOrAdd(
                        index = indexById,
                        itemToProcess = ItemState(id = id, isLoading = true)
                    )
                },
                onError = { id, message ->
                    downloadedPublicFeeds.apply {
                        downloadedPublicFeeds.replaceByIndex(
                            index = indexById,
                            itemToReplace = ItemState(
                                id = id,
                                isError = true,
                                errorMessage = message
                            )
                        )
                    }
                },
                onSuccess = { id, data ->
                    downloadedPublicFeeds.apply {
                        downloadedPublicFeeds.replaceByIndex(
                            index = indexById,
                            itemToReplace = ItemState(
                                id = id,
                                isSuccess = true,
                                data = data
                            )
                        )
                    }
                },
                onDelete = { id ->
                    downloadedPublicFeeds.removeAll {
                        it.id == id
                    }
                },
                onEdit = { id, oldId ->
                    downloadedPublicFeeds.removeAll {
                        it.id == oldId
                    }
                    updatePublicFeedTagByTag(id)
                }
            )
            updatePublicFeedListState()
        }.launchIn(viewModelScope)
    }

    private fun updatePublicFeedListState() {
        downloadedPublicFeeds.sortBy { it.id }
        _publicFeedListState.value = downloadedPublicFeeds.toList()
    }
}