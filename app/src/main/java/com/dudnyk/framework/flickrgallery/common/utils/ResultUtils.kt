package com.dudnyk.framework.flickrgallery.common.utils

import com.dudnyk.framework.flickrgallery.common.sealed.*

object ResultUtils {

    fun <T, R> processResult(
        result: Result<T, R>,
        onLoading: (R) -> Unit,
        onSuccess: (R, T) -> Unit,
        onError: (R, String) -> Unit,
        onDelete: (R) -> Unit,
        onEdit: (R, R) -> Unit
    ) {
        when (result) {
            is ErrorResult -> {
                onError(result.id, result.message)
            }
            is LoadingResult -> {
                onLoading(result.id)
            }
            is SuccessResult -> {
                onSuccess(result.id, result.data)
            }
            is DeleteResult -> {
                onDelete(result.id)
            }
            is EditResult -> {
                onEdit(result.id, result.oldId)
            }
        }
    }
}