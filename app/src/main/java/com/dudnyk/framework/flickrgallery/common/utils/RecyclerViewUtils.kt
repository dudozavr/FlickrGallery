package com.dudnyk.framework.flickrgallery.common.utils

import android.os.Parcelable
import com.dudnyk.framework.flickrgallery.presentation.ui.home.ItemState

object RecyclerViewUtils {

    fun <T : Parcelable, R : Parcelable> renderResult(
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