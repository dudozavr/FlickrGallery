package com.dudnyk.framework.flickrgallery.common

object Constants {
    const val BASE_URL = "https://www.flickr.com/"
    const val DATABASE_NAME = "flickr_db"
    const val GROUP_SEARCH_METHOD = "flickr.groups.search"
    const val OBTAINING_PHOTO_FROM_GROUP_METHOD = "flickr.groups.pools.getPhotos"
    const val PHOTO_EXTRAS = "url_h"
    const val HTTP_RESPONSE_FORMAT = "json"
    const val NO_JSON_CALL_BACK_VALUE = 1
    const val MAX_PAGE_SIZE = 50
    const val DEFAULT_PAGE_SIZE = 50
    const val DEFAULT_PUBLIC_FEED_TAG_NAME = "Public Feed"
    const val TAG_FILTER_ALL = "ALL"
    const val TAG_FILTER_ANY = "ANY"
    const val DELAY_AFTER_DOWNLOADING_DATA_FROM_NETWORK = 2000L
}