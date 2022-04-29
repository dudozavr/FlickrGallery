package com.dudnyk.framework.flickrgallery.domain.use_case

import com.dudnyk.framework.flickrgallery.domain.repository.Repository

class getGroupsByQueryKeyUseCase(
    private val repository: Repository
) {

    suspend operator fun invoke(queryKey: String) {

    }
}