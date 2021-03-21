package com.example.erc_project.view.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.erc_project.R
import com.example.erc_project.mdoel.data.CollectionItem

class CollectionAdapter(private val onItemClick: (CollectionItem) -> Unit):
    PagedListAdapter<CollectionItem, CollectionAdapter.CollectionViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollectionViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_view_collection, parent, false)

        return CollectionViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CollectionViewHolder, position: Int) {
        getItem(position)?.let {
            holder.tvName.text = it.name

            Glide.with(holder.itemView.context)
                .load(it.imgUrl)
                .into(holder.imageView)
        }


    }

    inner class CollectionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val tvName: TextView = itemView.findViewById(R.id.text_item_view_collection)
        val imageView: ImageView = itemView.findViewById(R.id.img_item_view_collection)

        init {
            itemView.setOnClickListener {
                getItem(adapterPosition)?.let { item ->
                    onItemClick(item)
                }
            }
        }
    }


    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<CollectionItem>() {
            override fun areItemsTheSame(oldItem: CollectionItem, newItem: CollectionItem): Boolean {
                return oldItem.tokenId == newItem.tokenId
            }

            override fun areContentsTheSame(oldItem: CollectionItem, newItem: CollectionItem): Boolean {
                return oldItem == newItem
            }
        }
    }

}