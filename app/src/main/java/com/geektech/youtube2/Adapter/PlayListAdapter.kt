package com.geektech.youtube2.Adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.youtube.model.Item
import com.geektech.youtube2.databinding.ItemPlaylistBinding
import com.geektech.youtube2.extens.load

class PlayListAdapter(
    private val list: ArrayList<Item>,
    private val onItemClick:
        (itemsId: String ) -> Unit?
) : RecyclerView.Adapter<PlayListAdapter.PlayListsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayListsViewHolder {
        val binding =
            ItemPlaylistBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PlayListsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PlayListsViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class PlayListsViewHolder(private var binding: ItemPlaylistBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun onBind(item: Item) {
            binding.ivVideos.load(item.snippet.thumbnails.medium.url)
            binding.tvTitle.text = item.snippet.title
            binding.tvDescription.text = item.contentDetails.itemCount.toString() + "video series"
            binding.root.setOnClickListener {
                onItemClick(item.id  )
            }
        }
    }
}