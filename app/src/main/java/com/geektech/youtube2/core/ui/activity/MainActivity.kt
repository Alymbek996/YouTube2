package com.geektech.youtube2.core.ui.activity

import android.content.Intent
import android.view.LayoutInflater
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider

import com.example.youtube.model.Item
import com.example.youtubeapi44.core.netwrok.result.Status
import com.example.youtubeapi44.core.ui.BaseActivity

import com.geektech.youtube2.Adapter.PlayListAdapter
import com.geektech.youtube2.core.ui.activity_playlist2.ActivityPlaylistVideo
import com.geektech.youtube2.databinding.ActivityMainBinding
import com.geektech.youtube2.utils.CheckNetworkConnection

class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>() {
    override val viewModel: MainViewModel
        get() = ViewModelProvider(this)[MainViewModel::class.java]

    override fun inflateViewBinding(inflater: LayoutInflater): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    private fun initRecView(playlistsList: List<Item>) {
        binding.recyclerView.adapter = PlayListAdapter(playlistsList as ArrayList<Item>, this::onItemClick)
    }

    private fun onItemClick(channelId: String,playlistTitle: String, playlistDescription: String) {
        Intent(this, ActivityPlaylistVideo::class.java).apply {
            putExtra(KEY_ChennelID, channelId)
            putExtra(KEY_playlistTitle,playlistTitle  )
            putExtra(KEY_playlistDescription, playlistDescription)
            startActivity(this)
        }
    }


    override fun initViewModel() {
        super.initViewModel()

        viewModel.getPlaylists().observe(this) {
            when(it.status) {
                Status.SUCCESS -> {
                    it.data?.let { it1 -> initRecView(it1.items) }
                    binding.progressBar.isVisible = false
                }
                Status.ERROR -> {
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                    binding.progressBar.isVisible = false
                }
                Status.LOADING -> binding.progressBar.isVisible = true
            }
        }
    }
        override fun checkInternet() {
            CheckNetworkConnection(this).observe(this) { isConnected ->
                binding.includeConnection.rlParent.isVisible = !isConnected
                binding.recyclerView.isVisible = isConnected
            }
        }


    companion object {
        const val  KEY_ChennelID = "KEY_ChennelID"
const val KEY_playlistTitle="KEY_playlistTitle"
        const val KEY_playlistDescription="KEY_playlistDescription"
    }
}