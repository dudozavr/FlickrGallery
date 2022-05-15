package com.dudnyk.framework.flickrgallery.presentation.ui.home.fragment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dudnyk.framework.flickrgallery.R
import com.dudnyk.framework.flickrgallery.databinding.LayoutHomeBinding
import com.dudnyk.framework.flickrgallery.domain.model.Photo
import com.dudnyk.framework.flickrgallery.domain.model.PublicFeedTag
import com.dudnyk.framework.flickrgallery.presentation.ui.home.PublicFeedItemActions
import com.dudnyk.framework.flickrgallery.presentation.ui.home.adapter.PublicFeedAdapter
import com.dudnyk.framework.flickrgallery.presentation.ui.home.fragment.PublicFeedTagDialogFragment.Companion.NEW_PUBLIC_FEED_TEG_KEY
import com.dudnyk.framework.flickrgallery.presentation.ui.home.fragment.PublicFeedTagDialogFragment.Companion.OLD_PUBLIC_FEED_TEG_KEY
import com.dudnyk.framework.flickrgallery.presentation.ui.home.viewmodel.HomeViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.viewmodel.ext.android.stateViewModel

class HomeFragment : Fragment() {

    private val homeViewModel by stateViewModel<HomeViewModel>()
    private var _binding: LayoutHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var publicFeedAdapter: PublicFeedAdapter
    private lateinit var publicFeedsRecycler: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = LayoutHomeBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initFields()
        customizeRecycleView()
        initObservers()
        homeViewModel.getPhotosFromPublicFeedByTags()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.toolbar_home, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.add_tag_button -> {
                findNavController().navigate(R.id.action_home_fragment_to_public_feed_tag_dialog_fragment)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initFields() {
        publicFeedsRecycler = binding.publicFeeds
        publicFeedAdapter = PublicFeedAdapter(object : PublicFeedItemActions {
            override fun onPublicFeedEdit(publicFeedTag: PublicFeedTag) {
                findNavController().navigate(
                    HomeFragmentDirections.actionHomeFragmentToPublicFeedTagDialogFragment().apply {
                        this.publicFeedTag = publicFeedTag
                    })
            }

            override fun onPublicFeedDelete(publicFeedTag: PublicFeedTag) {
                homeViewModel.deletePublicFeedTagByTag(publicFeedTag)
            }

            override fun onPublicFeedTryAgain(publicFeedTag: PublicFeedTag) {
                homeViewModel.updatePublicFeedTagByTag(publicFeedTag)
            }

            override fun onPhotoClick(photo: Photo) {
                findNavController().navigate(
                    HomeFragmentDirections.actionHomeFragmentToPhotoDetailFragment(photo)
                )
            }
        })
    }

    private fun initObservers() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            homeViewModel.publicFeedListState.collect { listOfPublicFeedStates ->
                if (listOfPublicFeedStates.isNotEmpty()) {
                    publicFeedAdapter.setPublicFeeds(listOfPublicFeedStates)
                }
            }
        }
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            findNavController().currentBackStackEntryFlow.collectLatest { navBackStackEntry ->
                navBackStackEntry.savedStateHandle.get<PublicFeedTag>(NEW_PUBLIC_FEED_TEG_KEY)
                    ?.let { newPublicFeedTag ->
                        navBackStackEntry.savedStateHandle.get<PublicFeedTag>(
                            OLD_PUBLIC_FEED_TEG_KEY
                        )?.let { oldPublicFeedTag ->
                            homeViewModel.editPublicFeedTag(oldPublicFeedTag, newPublicFeedTag)
                        } ?: homeViewModel.insertAndDisplayPublicFeedTag(newPublicFeedTag)
                    }
                navBackStackEntry.savedStateHandle.remove<PublicFeedTag>(NEW_PUBLIC_FEED_TEG_KEY)
                navBackStackEntry.savedStateHandle.remove<PublicFeedTag>(OLD_PUBLIC_FEED_TEG_KEY)
            }
        }
    }

    private fun customizeRecycleView() {
        publicFeedsRecycler.apply {
            adapter = publicFeedAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }
    }
}