package com.dudnyk.framework.flickrgallery.presentation.ui.search.fragment

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dudnyk.framework.flickrgallery.R
import com.dudnyk.framework.flickrgallery.databinding.LayoutSearchBinding
import com.dudnyk.framework.flickrgallery.domain.model.PhotoGroup
import com.dudnyk.framework.flickrgallery.presentation.ui.search.GroupItemActions
import com.dudnyk.framework.flickrgallery.presentation.ui.search.adapter.GroupRecyclerViewAdapter
import com.dudnyk.framework.flickrgallery.presentation.ui.search.viewmodel.SearchViewModel
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment : Fragment() {

    private val searchViewModel by viewModel<SearchViewModel>()
    private var _binding: LayoutSearchBinding? = null
    private val binding get() = _binding!!
    private val photoGroupAdapter = GroupRecyclerViewAdapter(object : GroupItemActions {
        override fun onItemClick(photoGroup: PhotoGroup) {
            findNavController().navigate(
                SearchFragmentDirections.actionSearchFragmentToLayoutPhotoFromGroupFragment(
                    photoGroup
                )
            )
        }
    })
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