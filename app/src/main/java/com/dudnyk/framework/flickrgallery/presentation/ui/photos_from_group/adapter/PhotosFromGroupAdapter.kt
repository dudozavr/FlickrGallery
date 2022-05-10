package com.dudnyk.framework.flickrgallery.presentation.ui.photos_from_group.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.dudnyk.framework.flickrgallery.databinding.LayoutPhotoItemBinding
import com.dudnyk.framework.flickrgallery.domain.model.Photo

class PhotosFromGroupAdapter :
    PagingDataAdapter<Photo, PhotosFromGroupAdapter.PhotosFromGroupViewHolder>(
        diffCallback = PhotoDiffUtil()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotosFromGroupViewHolder {
        return PhotosFromGroupViewHolder(
            LayoutPhotoItemBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: PhotosFromGroupViewHolder, position: Int) {
        getItem(position)?.let { photo ->
            holder.setPhoto(photo)
        }
    }

    inner class PhotosFromGroupViewHolder(
        private val binding: LayoutPhotoItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun setPhoto(photoItem: Photo) {
            with(binding) {
                photoTitle.text = photoItem.title
                Glide.with(root.context).load(photoItem.smallPhotoUrl)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(photo)
            }
        }
    }
}