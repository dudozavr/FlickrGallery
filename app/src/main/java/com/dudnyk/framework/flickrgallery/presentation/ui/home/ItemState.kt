package com.dudnyk.framework.flickrgallery.presentation.ui.home

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ItemState<T : Parcelable, R : Parcelable>(
    val id: R,
    val data: T? = null,
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""
) : Parcelable