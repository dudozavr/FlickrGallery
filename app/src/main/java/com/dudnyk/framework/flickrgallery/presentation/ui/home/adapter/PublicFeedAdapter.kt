package com.dudnyk.framework.flickrgallery.presentation.ui.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dudnyk.framework.flickrgallery.R
import com.dudnyk.framework.flickrgallery.common.PublicFeedState
import com.dudnyk.framework.flickrgallery.common.extension.ViewExtension.setVisibility
import com.dudnyk.framework.flickrgallery.common.utils.RecyclerViewUtils.renderResult
import com.dudnyk.framework.flickrgallery.databinding.LayoutGroupItemBinding
import com.dudnyk.framework.flickrgallery.databinding.LayoutPartResultBinding
import com.dudnyk.framework.flickrgallery.domain.model.PublicFeed
import com.dudnyk.framework.flickrgallery.domain.model.PublicFeedTag
import com.dudnyk.framework.flickrgallery.presentation.ui.home.adapter.item_decoration.HorizontalItemDecoration
import com.dudnyk.framework.flickrgallery.presentation.ui.home.PublicFeedActions
import com.dudnyk.framework.flickrgallery.presentation.ui.home.adapter.diff_util.PublicFeedDiffUtil

class PublicFeedAdapter(private val actionListener: PublicFeedActions) :
    RecyclerView.Adapter<PublicFeedAdapter.PublicFeedViewHolder>(), View.OnClickListener {

    private var publicFeeds = emptyList<PublicFeedState>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PublicFeedViewHolder {
        val binding = LayoutGroupItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        val resultBinding = LayoutPartResultBinding.bind(binding.root)
        binding.editButton.setOnClickListener(this)
        binding.deleteButton.setOnClickListener(this)
        resultBinding.tryAgainButton.setOnClickListener(this)

        return PublicFeedViewHolder(binding, resultBinding)
    }

    override fun onBindViewHolder(holder: PublicFeedViewHolder, position: Int) {
        holder.setPublicFeed(publicFeeds[position])
    }

    override fun getItemCount() = publicFeeds.size

    override fun onClick(v: View?) {
        v?.let { view ->
            val publicFeedTag = view.tag as PublicFeedTag
            when (view.id) {
                R.id.edit_button -> actionListener.onPublicFeedEdit(publicFeedTag = publicFeedTag)
                R.id.try_again_button -> actionListener.onPublicFeedTryAgain(publicFeedTag = publicFeedTag)
                else -> {
                    actionListener.onPublicFeedDelete(publicFeedTag = publicFeedTag)
                }
            }
        }
    }

    fun setPublicFeeds(publicFeedsToSet: List<PublicFeedState>) {
        val oldList = publicFeeds.toList()
        publicFeeds = publicFeedsToSet
        DiffUtil.calculateDiff(PublicFeedDiffUtil(oldList, publicFeeds.toList())).also { result ->
            result.dispatchUpdatesTo(this)
        }
    }

    inner class PublicFeedViewHolder(
        private val binding: LayoutGroupItemBinding,
        private val resultBinding: LayoutPartResultBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        private val childAdapter = PublicFeedPhotoAdapter(actionListener)

        fun setPublicFeed(publicFeedState: PublicFeedState) {
            renderResult(
                itemState = publicFeedState,
                onError = { onErrorResult(errorMessageText = publicFeedState.errorMessage) },
                onSuccess = { onSuccessResult(publicFeedState.data!!) }
            )
            setupViewsVisibility(publicFeedState)
            setGroupTitle(publicFeedState.id.tagName)
            initViewTags(publicFeedState)
        }

        private fun onErrorResult(errorMessageText: String) {
            with(resultBinding) {
                errorMessage.text = errorMessageText.ifBlank {
                    binding.root.context.getString(R.string.default_error_text)
                }
            }
        }

        private fun onSuccessResult(publicFeed: PublicFeed) {
            with(binding) {
                listOfPhotos.apply {
                    adapter = childAdapter
                    layoutManager =
                        LinearLayoutManager(root.context, LinearLayoutManager.HORIZONTAL, false)
                    if (itemDecorationCount == 0) {
                        addItemDecoration(HorizontalItemDecoration())
                    }
                }
                childAdapter.setPublicFeedPhotosData(publicFeed.photos)
            }
        }

        private fun setupViewsVisibility(itemState: PublicFeedState) {
            with(binding) {
                listOfPhotos.setVisibility(itemState.isSuccess)
                groupItemButtons.setVisibility(!itemState.isLoading && !itemState.id.isDefaultTag)
            }
            with(resultBinding) {
                progressBar.setVisibility(itemState.isLoading)
                errorContainer.setVisibility(itemState.isError)
                emptyPhotoListTextview.setVisibility(itemState.isSuccess && itemState.data!!.photos.isEmpty())
            }
        }

        private fun setGroupTitle(titleText: String) {
            with(binding) {
                groupName.text = titleText
            }
        }

        private fun initViewTags(publicFeedState: PublicFeedState) {
            with(binding) {
                editButton.tag = publicFeedState.id
                deleteButton.tag = publicFeedState.id
            }
            with(resultBinding) {
                tryAgainButton.tag = publicFeedState.id
            }
        }
    }
}