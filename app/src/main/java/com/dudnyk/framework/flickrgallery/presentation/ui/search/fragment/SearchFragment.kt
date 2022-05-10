package com.dudnyk.framework.flickrgallery.presentation.ui.search.fragment

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
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
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.toolbar_search, menu)
        val searchView = menu.findItem(R.id.search_button)
        initToolbarSearchViewListener(searchView.actionView as SearchView)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.search_button -> {
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initFields() {
        listOfGroupsRecyclerView = binding.listOfGroups
    }

    private fun setUpRecycleViews() {
        listOfGroupsRecyclerView.apply {
            adapter = photoGroupAdapter
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        }
    }

    private fun initToolbarSearchViewListener(searchView: SearchView) {
        searchView.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchViewModel.getGroupByQueryKey(query ?: "")
                callPagePhotoGroupStateFlow()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                searchViewModel.getGroupByQueryKey(newText ?: "")
                callPagePhotoGroupStateFlow()
                return true
            }
        })
    }

    private fun callPagePhotoGroupStateFlow() {
        lifecycleScope.launchWhenStarted {
            searchViewModel.pagePhotoGroupState.collectLatest { photoGroupPagingData ->
                photoGroupAdapter.submitData(photoGroupPagingData)
            }
        }
    }
}