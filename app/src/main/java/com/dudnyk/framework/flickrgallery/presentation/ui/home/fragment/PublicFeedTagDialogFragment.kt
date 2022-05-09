package com.dudnyk.framework.flickrgallery.presentation.ui.home.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatCheckBox
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.dudnyk.framework.flickrgallery.databinding.LayoutDialogAddTagBinding
import com.dudnyk.framework.flickrgallery.domain.model.PublicFeedTag
import com.google.android.material.textfield.TextInputEditText

class PublicFeedTagDialogFragment : AppCompatDialogFragment() {

    companion object {
        const val NEW_PUBLIC_FEED_TEG_KEY = "NEW_PUBLIC_FEED_TEG_KEY"
        const val OLD_PUBLIC_FEED_TEG_KEY = "OLD_PUBLIC_FEED_TEG_KEY"
    }

    private val args: PublicFeedTagDialogFragmentArgs by navArgs()
    private var _binding: LayoutDialogAddTagBinding? = null
    private val binding get() = _binding!!
    private lateinit var tagInputTextView: TextInputEditText
    private lateinit var fullMatchSearchCheckBox: AppCompatCheckBox
    private lateinit var cancelButton: AppCompatButton
    private lateinit var submitButton: AppCompatButton

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = LayoutDialogAddTagBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initFields()
        initListeners()
        setupFields()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initFields() {
        tagInputTextView = binding.tagInput
        fullMatchSearchCheckBox = binding.fullMatchSearchCheckBox
        cancelButton = binding.cancelButton
        submitButton = binding.submitButton
    }

    private fun initListeners() {
        submitButton.setOnClickListener {
            if (!tagInputTextView.text.isNullOrBlank()) {
                findNavController().previousBackStackEntry?.savedStateHandle?.apply {
                    set(
                        NEW_PUBLIC_FEED_TEG_KEY, PublicFeedTag(
                            tagName = tagInputTextView.text.toString(),
                            isMustContainAllTags = fullMatchSearchCheckBox.isChecked
                        )
                    )
                    set(
                        OLD_PUBLIC_FEED_TEG_KEY, args.publicFeedTag
                    )
                }
            }
            findNavController().navigateUp()
        }
        cancelButton.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun setupFields() {
        args.publicFeedTag?.let { publicFeedTag ->
            tagInputTextView.setText(publicFeedTag.tagName)
            fullMatchSearchCheckBox.isChecked = publicFeedTag.isMustContainAllTags
        }
    }
}