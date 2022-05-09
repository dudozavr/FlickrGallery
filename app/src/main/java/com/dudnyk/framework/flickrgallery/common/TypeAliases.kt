package com.dudnyk.framework.flickrgallery.common

import com.dudnyk.framework.flickrgallery.common.sealed.Result
import com.dudnyk.framework.flickrgallery.domain.model.PublicFeed
import com.dudnyk.framework.flickrgallery.domain.model.PublicFeedTag
import com.dudnyk.framework.flickrgallery.presentation.ui.home.ItemState

typealias PublicFeedState = ItemState<PublicFeed, PublicFeedTag>
typealias PublicFeedResult = Result<PublicFeed, PublicFeedTag>