package com.geektech.youtube2.core.ui.activity_playlist2

import androidx.lifecycle.LiveData
import com.example.youtube.model.Item
import com.geektech.youtube2.repository.Repository

import com.example.youtubeapi44.core.netwrok.result.Resource
import com.example.youtubeapi44.core.ui.BaseViewModel

class PlayVideoListViewModel: BaseViewModel() {
    private val repository = Repository()

    fun getPlayVideoList(playlistId: String):LiveData<Resource<Item>>{
        return repository.getPlayVideoList(playlistId)
    }

}