package com.dudnyk.framework.flickrgallery.data.local.dao

import androidx.room.*
import com.dudnyk.framework.flickrgallery.data.model.dto.public_feed.PublicFeedTagEntity

@Dao
interface PublicFeedTagDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPublicFeedTag(publicFeedTagEntity: PublicFeedTagEntity)

    @Delete
    suspend fun deletePublicFeedTag(publicFeedTagEntity: PublicFeedTagEntity)

    @Query("SELECT * FROM public_feed_tag ORDER BY tagName")
    suspend fun getPublicFeedTags(): List<PublicFeedTagEntity>

    @Transaction
    suspend fun deleteOldAndInsertNewPublicFeedTag(
        oldPublicFeedTagEntity: PublicFeedTagEntity,
        newPublicFeedTagEntity: PublicFeedTagEntity
    ) {
        deletePublicFeedTag(oldPublicFeedTagEntity)
        insertPublicFeedTag(newPublicFeedTagEntity)
    }
}