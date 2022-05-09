package com.dudnyk.framework.flickrgallery.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dudnyk.framework.flickrgallery.data.local.dao.PublicFeedTagDao
import com.dudnyk.framework.flickrgallery.data.model.dto.public_feed.PublicFeedTagEntity

@Database(
    entities = [PublicFeedTagEntity::class],
    version = 1
)
abstract class FlickrDataBase : RoomDatabase() {

    abstract fun getPublicFeedTagDao(): PublicFeedTagDao
}