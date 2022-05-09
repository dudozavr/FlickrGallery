package com.dudnyk.framework.flickrgallery.presentation.ui.search.fragment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dudnyk.framework.flickrgallery.R
import com.dudnyk.framework.flickrgallery.databinding.LayoutSearchBinding
import com.dudnyk.framework.flickrgallery.presentation.ui.search.adapter.GroupRecyclerViewAdapter
import com.dudnyk.framework.flickrgallery.presentation.ui.search.viewmodel.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private val searchViewModel by viewModels<SearchViewModel>()
    private var _binding: LayoutSearchBinding? = null
    private val binding get() = _binding!!
    private val photoGroupAdapter = GroupRecyclerViewAdapter()
    private lateinit var listOfGroupsRecyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = LayoutSearchBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initFields()
        setUpRecycleViews()
        initObservers()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.toolbar_search, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.submit_button -> {
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initFields() {
        listOfGroupsRecyclerView = binding.listOfGroups
    }

    private fun initObservers() {
        lifecycleScope.launchWhenStarted {
            searchViewModel.pagePhotoGroupState.collectLatest { photoGroupPagingData ->
                photoGroupAdapter.submitData(photoGroupPagingData)
            }
        }
    }

    private fun setUpRecycleViews() {
        listOfGroupsRecyclerView.apply {
            adapter = photoGroupAdapter
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        }
    }
}