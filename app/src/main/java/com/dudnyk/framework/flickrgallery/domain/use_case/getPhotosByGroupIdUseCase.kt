package com.dudnyk.framework.flickrgallery.domain.use_case

import com.dudnyk.framework.flickrgallery.common.Resource
import com.dudnyk.framework.flickrgallery.domain.model.Photo
import com.dudnyk.framework.flickrgallery.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.net.UnknownHostException
import javax.inject.Inject

class getPhotosByGroupIdUseCase @Inject constructor(
    private val repository: Repository
) {

    suspend operator fun invoke(groupId: String): Flow<Resource<List<Photo>>> = flow {
        try {
            emit(Resource.Loading(null))
            val listOfPhotos = repository.getPhotosByGroupId(groupId = groupId)
            emit(Resource.Success(listOfPhotos))
        } catch (e: Exception) {
            when (e) {
                is HttpException, is UnknownHostException -> emit(
                    Resource.NetworkError(
                        message = e.localizedMessage
                            ?: "Couldn't reach server. Check your internet connection"
                    )
                )
                else -> emit(
                    Resource.Error(
                        message = e.localizedMessage
                            ?: "An unexpected error occurred"
                    )
                )
            }
        }
    }
}