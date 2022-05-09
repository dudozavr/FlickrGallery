package com.dudnyk.framework.flickrgallery.common.utils

import com.dudnyk.framework.flickrgallery.presentation.ui.home.ItemState

object RecyclerViewUtils {

    fun <T, R> renderResult(
        itemState: ItemState<T, R>,
        onSuccess: (T?) -> Unit,
        onError: (String) -> Unit
    ) {
        itemState.run {
            when {
                isError -> {
                    onError(errorMessage)
                }
                isSuccess -> {
                    onSuccess(data)
                }
            }
        }
    }
}