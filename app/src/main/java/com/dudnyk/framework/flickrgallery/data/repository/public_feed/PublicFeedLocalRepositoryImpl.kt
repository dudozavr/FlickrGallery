package com.dudnyk.framework.flickrgallery.data.repository.public_feed

import com.dudnyk.framework.flickrgallery.data.local.dao.PublicFeedTagDao
import com.dudnyk.framework.flickrgallery.data.model.dto.public_feed.PublicFeedTagEntity
import com.dudnyk.framework.flickrgallery.data.model.dto.public_feed.toPublicFeedTag
import com.dudnyk.framework.flickrgallery.domain.model.PublicFeedTag
import com.dudnyk.framework.flickrgallery.domain.model.toPublicFeedTagEntity
import com.dudnyk.framework.flickrgallery.domain.repository.public_feed.PublicFeedLocalRepository
import javax.inject.Inject

class PublicFeedLocalRepositoryImpl @Inject constructor(
    private val publicFeedTagDao: PublicFeedTagDao
) : PublicFeedLocalRepository {

    override suspend fun getPublicFeedTags() =
        publicFeedTagDao.getPublicFeedTags().map { it.toPublicFeedTag() }

    override suspend fun insertPublicFeedTag(publicFeedTag: PublicFeedTag) {
        publicFeedTagDao.insertPublicFeedTag(publicFeedTag.toPublicFeedTagEntity())
    }

    override suspend fun deletePublicFeedTag(publicFeedTag: PublicFeedTag) {
        publicFeedTagDao.deletePublicFeedTag(publicFeedTag.toPublicFeedTagEntity())
    }

    override suspend fun deleteOldAndInsertNewPublicFeedTag(
        oldPublicFeedTag: PublicFeedTag,
        newPublicFeedTag: PublicFeedTag
    ) {
        publicFeedTagDao.deleteOldAndInsertNewPublicFeedTag(
            oldPublicFeedTag.toPublicFeedTagEntity(), newPublicFeedTag.toPublicFeedTagEntity()
        )
    }
}