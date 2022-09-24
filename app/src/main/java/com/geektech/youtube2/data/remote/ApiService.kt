package com.geektech.youtube2.data.remote

import com.example.youtube.model.Item
import com.example.youtube.model.Playlists
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("playlists")
    fun getPlaylists(
        @Query("channelId") channelId: String,
        @Query("part") part : String,
        @Query("key")apiKey:String,
        @Query ("maxResults") maxResults : Int = 50
    ):Call<Playlists>

    @GET("playlistItems")
    fun getPlaylistItems(
        @Query("part") part: String,
        @Query("playlistId") playlistId: String,
        @Query("key") apiKey: String,
        @Query("maxResults") maxResult: Int= 50
    ): Call<Item>
}