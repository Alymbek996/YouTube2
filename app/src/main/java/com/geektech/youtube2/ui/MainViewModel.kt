package com.geektech.youtube2.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.youtube.model.Playlists
import com.geektech.youtube2.BuildConfig
import com.geektech.youtube2.Constant
import com.geektech.youtube2.base.BaseViewModel
import com.geektech.youtube2.remote.ApiService
import com.geektech.youtube2.remote.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel: BaseViewModel() {

    private val apiService: ApiService by lazy{
        RetrofitClient.create()
    }

    fun playlists():LiveData<Playlists>{
        return getPlaylists()
    }

    private fun getPlaylists(): LiveData<Playlists> {

        val data =MutableLiveData<Playlists>()
        apiService.getPlaylists(Constant.channelId, Constant.part, BuildConfig.API_KEY).enqueue(object:Callback<Playlists>{
            override fun onResponse(call: Call<Playlists>, response: Response<Playlists>) {
                if (response.isSuccessful){
                    data.value = response.body()
                }
            }

            override fun onFailure(call: Call<Playlists>, t: Throwable) {

            }

        })
        return data
    }
}