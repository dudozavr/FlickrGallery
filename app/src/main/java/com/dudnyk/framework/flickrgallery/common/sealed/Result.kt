package com.dudnyk.framework.flickrgallery.common.sealed

sealed class Result<T, R>(val id: R)

class LoadingResult<T, R>(id: R) : Result<T, R>(id)
class SuccessResult<T, R>(id: R, val data: T) : Result<T, R>(id)
class ErrorResult<T, R>(id: R, val message: String) : Result<T, R>(id)
class DeleteResult<T, R>(id: R) : Result<T, R>(id)
class EditResult<T, R>(id: R, val oldId: R) : Result<T, R>(id)