package com.dudnyk.framework.flickrgallery.presentation.ui.photos_from_group.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dudnyk.framework.flickrgallery.R
import com.dudnyk.framework.flickrgallery.databinding.LayoutPhotosFromGroupBinding
import com.dudnyk.framework.flickrgallery.domain.model.Photo
import com.dudnyk.framework.flickrgallery.presentation.ui.photos_from_group.PhotosFromGroupActions
import com.dudnyk.framework.flickrgallery.presentation.ui.photos_from_group.adapter.GridItemDecoration
import com.dudnyk.framework.flickrgallery.presentation.ui.photos_from_group.adapter.PhotosFromGroupAdapter
import com.dudnyk.framework.flickrgallery.presentation.ui.photos_from_group.viewmodel.PhotosFromGroupViewModel
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.viewmodel.ext.android.viewModel

class PhotosFromGroupFragment : Fragment() {

    private val args: PhotosFromGroupFragmentArgs by navArgs()
    private val photosFromGroupViewModel by viewModel<PhotosFromGroupViewModel>()
    private var _binding: LayoutPhotosFromGroupBinding? = null
    private val binding get() = _binding!!
    private val photoAdapter = PhotosFromGroupAdapter(object : PhotosFromGroupActions {
        override fun onPhotoClick(photo: Photo) {
            findNavController().navigate(
                PhotosFromGroupFragmentDirections.actionLayoutPhotosFromGroupFragmentToPhotoDetailFragment(
                    photo
                )
            )
        }
    })
    private lateinit var listOfPhotosRecyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = LayoutPhotosFromGroupBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        photosFromGroupViewModel.getPhotosByGroupId(args.photoGroup.id)
        initFields()
        setUpRecycleViews()
        initObservers()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initFields() {
        listOfPhotosRecyclerView = binding.listOfPhotos
    }

    private fun initObservers() {
        lifecycleScope.launchWhenStarted {
            photosFromGroupViewModel.pagePhotoState.collectLatest { photoPagingData ->
                photoAdapter.submitData(photoPagingData)
            }
        }
    }

    private fun setUpRecycleViews() {
        val columnCount = resources.getInteger(R.integer.grid_column_count)
        listOfPhotosRecyclerView.apply {
            adapter = photoAdapter
            layoutManager = GridLayoutManager(
                requireContext(),
                columnCount
            )
            addItemDecoration(GridItemDecoration(columnCount))
        }
    }
}