package com.dudnyk.framework.flickrgallery.common.extension

import java.util.*

object MutableListExtension {

    fun <T> MutableList<T>.replaceByIndex(index: Int, itemToReplace: T) {
        Collections.replaceAll(this, this[index], itemToReplace)
    }

    fun <T> MutableList<T>.replaceByIndexOrAdd(index: Int, itemToProcess: T) {
        if (index == -1) {
            add(itemToProcess)
        } else {
            replaceByIndex(index, itemToProcess)
        }
    }
}