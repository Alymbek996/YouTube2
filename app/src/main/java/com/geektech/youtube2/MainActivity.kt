package com.geektech.youtube2

import android.content.Intent
import android.view.LayoutInflater
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider

import com.example.youtube.model.Item

import com.geektech.youtube2.Adapter.PlayListAdapter
import com.geektech.youtube2.base.BaseActivity
import com.geektech.youtube2.databinding.ActivityMainBinding
import com.geektech.youtube2.ui.MainViewModel
import com.geektech.youtube2.ui.UIActivity1

class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>() {
    override val viewModel: MainViewModel
        get() = ViewModelProvider(this)[MainViewModel::class.java]

    override fun inflateViewBinding(inflater: LayoutInflater): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    private fun initRecView(playlistsList: List<Item>) {
        binding.recyclerView.adapter = PlayListAdapter(playlistsList as ArrayList<Item>, this::onItemClick)
    }

    private fun onItemClick(channelId: String) {
        Intent(this, UIActivity1::class.java).apply {
            putExtra(KEY_ChennelID, channelId)
            startActivity(this)
        }
    }


    override fun initViewModel() {
        super.initViewModel()
        viewModel.playlists().observe(this) {
            Toast.makeText(this, it.kind, Toast.LENGTH_SHORT).show()
            initRecView(it.items)
        }

    }

    companion object {
        const val  KEY_ChennelID = "KEY_ChennelID"

    }
}