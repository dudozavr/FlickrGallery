package com.dudnyk.framework.flickrgallery.common.utils

import androidx.paging.PagingSource

object PagingUtils {

    fun <T: Any> getEmptyPage(): PagingSource.LoadResult<Int, T> {
        return PagingSource.LoadResult.Page(emptyList(), prevKey = null, nextKey = null)
    }
}