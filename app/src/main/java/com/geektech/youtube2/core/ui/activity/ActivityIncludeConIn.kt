package com.geektech.youtube2.core.ui.activity

import android.view.LayoutInflater
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.youtubeapi44.core.ui.BaseActivity

import com.geektech.youtube2.databinding.ActivityIncludeConInBinding

class ActivityIncludeConIn : BaseActivity<MainViewModel, ActivityIncludeConInBinding>() {
    private var playlistId: String? = null

    override val viewModel: MainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }
    override fun inflateViewBinding(inflater: LayoutInflater): ActivityIncludeConInBinding {
        return ActivityIncludeConInBinding.inflate(layoutInflater)
    }

    override fun initViewModel() {
        super.initViewModel()
        playlistId = intent.getStringExtra(MainActivity.KEY_ChennelID).toString()
        Toast.makeText(this, playlistId, Toast.LENGTH_SHORT).show()
    }
}