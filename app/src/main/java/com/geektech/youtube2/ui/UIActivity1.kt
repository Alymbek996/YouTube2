package com.geektech.youtube2.ui

import android.view.LayoutInflater
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider

import com.geektech.youtube2.MainActivity
import com.geektech.youtube2.base.BaseActivity
import com.geektech.youtube2.databinding.ActivityUiactivity1Binding

class UIActivity1 : BaseActivity<MainViewModel, ActivityUiactivity1Binding>() {
    private var playlistId: String? = null

    override val viewModel: MainViewModel  by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }
    override fun inflateViewBinding(inflater: LayoutInflater): ActivityUiactivity1Binding {
        return ActivityUiactivity1Binding.inflate(layoutInflater)
    }

    override fun initViewModel() {
        super.initViewModel()
        playlistId = intent.getStringExtra(MainActivity.KEY_ChennelID).toString()
        Toast.makeText(this, playlistId, Toast.LENGTH_SHORT).show()
    }
}