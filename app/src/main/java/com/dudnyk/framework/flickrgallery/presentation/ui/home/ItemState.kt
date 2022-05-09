package com.dudnyk.framework.flickrgallery.presentation.ui.home

data class ItemState<T, R>(
    val id: R,
    val data: T? = null,
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""
)