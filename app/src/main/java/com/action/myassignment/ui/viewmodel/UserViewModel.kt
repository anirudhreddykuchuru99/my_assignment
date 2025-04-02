package com.action.myassignment.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.action.myassignment.data.datasource.UserRepository
import com.action.myassignment.data.entity.UserResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.action.myassignment.util.Result

@HiltViewModel
class UserViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel() {

    private val _userState = MutableStateFlow<Result<UserResponse>>(Result.Loading)
    val userState: StateFlow<Result<UserResponse>> = _userState

    init {
        fetchUsers()
    }

    private fun fetchUsers() {
        viewModelScope.launch {
            userRepository.getUsers().collect { result ->
                _userState.value = result
            }
        }
    }
}
