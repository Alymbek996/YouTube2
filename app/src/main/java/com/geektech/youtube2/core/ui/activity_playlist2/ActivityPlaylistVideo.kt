package com.geektech.youtube2.core.ui.activity_playlist2

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.example.youtube.model.Item
import com.example.youtubeapi44.core.netwrok.result.Status
import com.example.youtubeapi44.core.ui.BaseActivity
import com.geektech.youtube2.Adapter.PlaylistsVideoAdapter
import com.geektech.youtube2.R
import com.geektech.youtube2.core.ui.activity.MainActivity
import com.geektech.youtube2.databinding.ActivityPlaylistVideoBinding
import com.geektech.youtube2.utils.CheckNetworkConnection

class ActivityPlaylistVideo  : BaseActivity<PlayVideoListViewModel, ActivityPlaylistVideoBinding>() {
    private var playlistId: String? = null
    override val viewModel:PlayVideoListViewModel
        get() = ViewModelProvider(this)[PlayVideoListViewModel::class.java]

    override fun inflateViewBinding(inflater: LayoutInflater): ActivityPlaylistVideoBinding {
        return ActivityPlaylistVideoBinding.inflate(inflater)
    }



    override fun initViewModel() {
        super.initViewModel()


    }

    @SuppressLint("SetTextI18n")
    private fun initVM() {
        playlistId?.let {
            viewModel.getPlayVideoList(it).observe(this) {
                when(it.status) {
                    Status.SUCCESS -> {
                        if (it.data != null) {

                            initRecView(it.data.items as ArrayList<Item>)
                            binding.progressBar.isVisible = false
                            binding.seriesTv.text = it.data.items.size.toString() + " video series"
                        } else {
                            Log.e("Error1", "error 1")
                        }
                    }
                    Status.ERROR -> {
                        Log.e("Error2", "error 2")
                        Toast.makeText(this, "Ошибка", Toast.LENGTH_SHORT).show()
                    }
                    Status.LOADING -> {
                        binding.progressBar.isVisible = true
                    }
                }
            }
        }
    }
    override fun initView() {
        super.initView()
        playlistId = intent.getStringExtra(MainActivity.KEY_ChennelID).toString()
        binding.playlistTitle.text = intent.getStringExtra(MainActivity.KEY_playlistTitle).toString()
        binding.playlistDescription.text = intent.getStringExtra(MainActivity.KEY_playlistDescription).toString()

        initVM()
    }
    private fun initRecView(playlistsList: ArrayList<Item>?) {
        binding.videosRecyclerView.adapter = PlaylistsVideoAdapter(playlistsList!!, this::onItemClick)
         binding.videosRecyclerView.visibility = View.VISIBLE
    }
    private fun onItemClick( string: String,string2: String,string1: String) {

    }
    override fun checkInternet() {
        CheckNetworkConnection(this).observe(this) { isConnected ->
            binding.includeConnection.rlParent.isVisible = !isConnected
            binding.videosRecyclerView.isVisible = isConnected
        }
    }
    override fun initListener() {
        binding.TVback.setOnClickListener{
            onBackPressed()
        }

    }



}