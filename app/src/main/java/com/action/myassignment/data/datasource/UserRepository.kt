package com.action.myassignment.data.datasource

import com.action.myassignment.data.entity.UserResponse
import kotlinx.coroutines.flow.Flow
import com.action.myassignment.util.Result

interface UserRepository {
    fun getUsers(): Flow<Result<UserResponse>>
}