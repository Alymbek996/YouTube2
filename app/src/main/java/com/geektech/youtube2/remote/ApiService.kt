package com.geektech.youtube2.remote

import com.example.youtube.model.Playlists
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("playlists")
    fun getPlaylists(
        @Query("channelId") channelId: String,
        @Query("part") part : String,
        @Query("key")apiKey:String
    ):Call<Playlists>
}