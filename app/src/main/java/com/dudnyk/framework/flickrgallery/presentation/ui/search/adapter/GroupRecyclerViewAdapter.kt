package com.dudnyk.framework.flickrgallery.presentation.ui.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.dudnyk.framework.flickrgallery.databinding.LayoutGroupItemBinding
import com.dudnyk.framework.flickrgallery.domain.model.PhotoGroup
import com.dudnyk.framework.flickrgallery.presentation.ui.search.GroupItemActions

class GroupRecyclerViewAdapter(private val groupItemActions: GroupItemActions) :
    PagingDataAdapter<PhotoGroup, GroupRecyclerViewAdapter.GroupViewHolder>(
        diffCallback = PhotoGroupDiffUtil()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroupViewHolder {
        return GroupViewHolder(
            LayoutGroupItemBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: GroupViewHolder, position: Int) {
        getItem(position)?.let { photoGroup ->
            holder.setGroupItem(photoGroup)
        }
    }

    inner class GroupViewHolder(
        private val binding: LayoutGroupItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        private var photoGroup: PhotoGroup? = null

        init {
            binding.arrowForwardButton.setOnClickListener {
                groupItemActions.onItemClick(photoGroup!!)
            }
        }

        fun setGroupItem(photoGroupItem: PhotoGroup) {
            this.photoGroup = photoGroupItem
            with(binding) {
                groupName.text = photoGroupItem.name.trim()
                Glide.with(root.context).load(photoGroupItem.groupIconUrl)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(groupIcon)
            }
        }
    }
}