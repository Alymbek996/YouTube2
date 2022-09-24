package com.geektech.youtube2.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.youtube.model.Item
import com.example.youtube.model.Playlists
import com.example.youtubeapi44.core.netwrok.result.Resource
import com.geektech.youtube2.BuildConfig
import com.geektech.youtube2.data.remote.ApiService
import com.geektech.youtube2.core.netwrok.result.RetrofitClient
import com.geektech.youtube2.utils.Constant
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository {
    private val apiService: ApiService by lazy{
        RetrofitClient.create()
    }
    fun getPlaylists(): LiveData<Resource<Playlists> >{

        val data =MutableLiveData<Resource<Playlists>>()

        data.value = Resource.loading(null)

        apiService.getPlaylists(Constant.channelId, Constant.part, BuildConfig.API_KEY).enqueue(object:Callback<Playlists>{
            override fun onResponse(call: Call<Playlists>, response: Response<Playlists>) {
                if (response.isSuccessful){
                    data.value = Resource.success(response.body())
                }
            }

            override fun onFailure(call: Call<Playlists>, t: Throwable) {
            data.value = Resource.error( null,t.message, null)

            }

        })
        return data
    }
    fun getPlayVideoList (playlistId: String):LiveData<Resource<Item>> {
        val data = MutableLiveData<Resource<Item>>()
        data.value = Resource.loading(null)
        apiService.getPlaylistItems(Constant.part,playlistId,BuildConfig.API_KEY,Constant.maxres).enqueue(object:Callback<Item>{
            override fun onResponse(call: Call<Item>, response: Response<Item>) {
                if (response.isSuccessful){
                    data.value = Resource.success(response.body())
                }
            }

            override fun onFailure(call: Call<Item>, t: Throwable) {
                data.value = Resource.error(null,t.message,null)
            }

        })
        return data
    }
}

