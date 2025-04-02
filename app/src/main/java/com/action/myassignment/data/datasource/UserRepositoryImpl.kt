package com.action.myassignment.data.datasource

import com.action.myassignment.data.api.ApiService
import com.action.myassignment.util.Result
import com.action.myassignment.data.entity.UserResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val apiService: ApiService) : UserRepository {
    override fun getUsers(): Flow<Result<UserResponse>> = flow {
        emit(Result.Loading)
        try {
            val response = apiService.getUsers()
            emit(Result.Success(response))
        } catch (e: Exception) {
            emit(Result.Error(e))
        }
    }
}