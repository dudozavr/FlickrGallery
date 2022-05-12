package com.dudnyk.framework.flickrgallery.presentation.ui.photo_detail.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.dudnyk.framework.flickrgallery.databinding.LayoutPhotoDetailBinding

class PhotoDetailFragment : Fragment() {

    private val args: PhotoDetailFragmentArgs by navArgs()
    private var _binding: LayoutPhotoDetailBinding? = null
    private val binding get() = _binding!!
    private lateinit var photoImageView: AppCompatImageView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = LayoutPhotoDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initField()
        setupPhoto()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initField() {
        photoImageView = binding.photo
    }

    private fun setupPhoto() {
        Glide.with(requireContext()).load(args.photo.largePhotoUrl).into(photoImageView)
    }
}