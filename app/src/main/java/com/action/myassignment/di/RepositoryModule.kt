package com.action.myassignment.di

import com.action.myassignment.data.api.ApiService
import com.action.myassignment.data.datasource.UserRepository
import com.action.myassignment.data.datasource.UserRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @Provides
    fun provideUserRepository(apiService: ApiService): UserRepository {
        return UserRepositoryImpl(apiService)
    }
}