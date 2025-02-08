package com.github.limerio.final_project_kotlin.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.limerio.final_project_kotlin.models.User
import com.github.limerio.final_project_kotlin.repositories.UsersRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class UsersViewModel : ViewModel() {
    val dataList = MutableStateFlow(emptyList<User>())
    val isLoading = MutableStateFlow(false)
    val error = MutableStateFlow<String?>(null)

    fun load() {
        isLoading.value = true
        viewModelScope.launch(Dispatchers.IO){
            val newValues = UsersRepository.findAll()
            dataList.value = newValues
            isLoading.value = false
        }
    }

    fun findById(userId: Int): User {
        var userCache = dataList.value.find { it.id == userId }

        if(userCache === null) {
            userCache = UsersRepository.findById(userId)
            dataList.value = dataList.value.plus(userCache)
        }

        return userCache
    }
}