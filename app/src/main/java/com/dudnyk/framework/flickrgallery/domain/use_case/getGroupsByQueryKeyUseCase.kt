package com.dudnyk.framework.flickrgallery.domain.use_case

import com.dudnyk.framework.flickrgallery.common.Resource
import com.dudnyk.framework.flickrgallery.domain.model.Group
import com.dudnyk.framework.flickrgallery.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.net.UnknownHostException
import kotlin.Exception

class getGroupsByQueryKeyUseCase(
    private val repository: Repository
) {

    suspend operator fun invoke(queryKey: String): Flow<Resource<List<Group>>> = flow {
        try {
            emit(Resource.Loading(null))
            val listOfGroup = repository.getGroupsByQueryKey(queryKey = queryKey)
            emit(Resource.Success(listOfGroup))
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