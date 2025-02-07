package com.github.limerio.final_project_kotlin.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.limerio.final_project_kotlin.models.Post
import com.github.limerio.final_project_kotlin.repositories.PostsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class PostsViewModel : ViewModel() {
    val dataList = MutableStateFlow(emptyList<Post>())
    val isLoading = MutableStateFlow(false)
    val error = MutableStateFlow<String?>(null)

    fun load() {
        isLoading.value = true
        viewModelScope.launch(Dispatchers.IO){
            val newValues = PostsRepository.findAll()
            dataList.value = newValues
            isLoading.value = false
        }
    }
}