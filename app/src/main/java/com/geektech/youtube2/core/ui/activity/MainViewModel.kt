package com.geektech.youtube2.core.ui.activity

import androidx.lifecycle.LiveData
import com.example.youtube.model.Playlists
import com.example.youtubeapi44.core.netwrok.result.Resource
import com.example.youtubeapi44.core.ui.BaseViewModel
import com.geektech.youtube2.repository.Repository
class MainViewModel: BaseViewModel() {
    private val repository = Repository()

    fun getPlaylists(): LiveData<Resource<Playlists>> {
        return repository.getPlaylists()
    }
}