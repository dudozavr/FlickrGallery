package com.dudnyk.framework.flickrgallery.presentation.ui.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.dudnyk.framework.flickrgallery.R
import com.dudnyk.framework.flickrgallery.databinding.LayoutPhotoItemBinding
import com.dudnyk.framework.flickrgallery.domain.model.Photo
import com.dudnyk.framework.flickrgallery.presentation.ui.home.PublicFeedItemActions
import com.dudnyk.framework.flickrgallery.presentation.ui.home.adapter.diff_util.PublicFeedPhotoDiffUtil

class PublicFeedPhotoAdapter(private val actionListener: PublicFeedItemActions) :
    RecyclerView.Adapter<PublicFeedPhotoAdapter.PublicFeedPhotoViewHolder>(), View.OnClickListener {

    private var publicFeedPhotosData: List<Photo> = emptyList()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PublicFeedPhotoViewHolder {
        val binding = LayoutPhotoItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        binding.photo.setOnClickListener(this)
        return PublicFeedPhotoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PublicFeedPhotoViewHolder, position: Int) {
        holder.setPhoto(publicFeedPhotosData[position])
    }

    override fun getItemCount() = publicFeedPhotosData.size

    override fun onClick(v: View?) {
        v?.let { view ->
            val photo = view.tag as Photo
            if (view.id == R.id.photo) {
                actionListener.onPhotoClick(photo)
            }
        }
    }

    fun setPublicFeedPhotosData(publicFeedPhotosData: List<Photo>) {
        val oldList = this.publicFeedPhotosData.toList()
        this.publicFeedPhotosData = publicFeedPhotosData
        DiffUtil.calculateDiff(PublicFeedPhotoDiffUtil(oldList, this.publicFeedPhotosData.toList()))
            .also { result ->
                result.dispatchUpdatesTo(this)
            }
    }

    inner class PublicFeedPhotoViewHolder(
        private val binding: LayoutPhotoItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun setPhoto(photoItem: Photo) {
            with(binding) {
                photo.tag = photoItem
                if (photoItem.title.isBlank()) {
                    photoTitle.text = root.context.getString(R.string.no_photo_title_text)
                } else {
                    photoTitle.text = photoItem.title
                }
                photoTitle.text = photoItem.title
                photoItem.largePhotoUrl?.let { photoUrl ->
                    Glide.with(root.context).load(photoUrl)
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .into(photo)
                } ?: photo.setImageDrawable(
                    AppCompatResources.getDrawable(
                        root.context,
                        R.drawable.empty_photo
                    )
                )
            }
        }
    }
}