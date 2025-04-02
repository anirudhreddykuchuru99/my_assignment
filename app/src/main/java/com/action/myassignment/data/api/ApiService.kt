package com.action.myassignment.data.api

import com.action.myassignment.data.entity.UserResponse
import retrofit2.http.GET

interface ApiService {
    @GET("users")
    suspend fun getUsers(): UserResponse

}

//https://reqres.in/api/users