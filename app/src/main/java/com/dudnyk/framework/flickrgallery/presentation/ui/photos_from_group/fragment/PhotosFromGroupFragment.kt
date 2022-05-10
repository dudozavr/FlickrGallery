package com.dudnyk.framework.flickrgallery.presentation.ui.photos_from_group.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dudnyk.framework.flickrgallery.databinding.LayoutPhotosFromGroupBinding
import com.dudnyk.framework.flickrgallery.presentation.ui.photos_from_group.adapter.PhotosFromGroupAdapter
import com.dudnyk.framework.flickrgallery.presentation.ui.photos_from_group.viewmodel.PhotosFromGroupViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class PhotosFromGroupFragment : Fragment() {

    private val args: PhotosFromGroupFragmentArgs by navArgs()
    private val photosFromGroupViewModel by viewModels<PhotosFromGroupViewModel>()
    private var _binding: LayoutPhotosFromGroupBinding? = null
    private val binding get() = _binding!!
    private val photoAdapter = PhotosFromGroupAdapter()
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
        listOfPhotosRecyclerView.apply {
            adapter = photoAdapter
            layoutManager = GridLayoutManager(requireContext(), 4)
        }
    }
}