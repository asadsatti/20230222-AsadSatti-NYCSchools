package com.example.nyc_school.presentation.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nyc_school.data.db.School
import com.example.nyc_school.databinding.FragmentSchoolListBinding
//import com.example.nyc_school.presentation.paging.asMergedLoadStates
import com.example.nyc_school.presentation.vm.SchoolListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.filter

@AndroidEntryPoint
class SchoolListFragment : Fragment() {

    private lateinit var binding: FragmentSchoolListBinding
    private val viewModel: SchoolListViewModel by viewModels()

    private val adapter = SchoolListAdapter {
        navigateToSchoolSatFragment(it)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)

        binding = FragmentSchoolListBinding.inflate(inflater, container, false)

        initAdapter()
        initSwipeToRefresh()

        return binding.root
    }

    private fun initAdapter() {
        binding.schoolList.adapter = adapter
            .withLoadStateHeaderAndFooter(
            header = SchoolLoadStateAdapter { adapter.retry() },
            footer = SchoolLoadStateAdapter { adapter.retry() }
        )

        binding.schoolList.apply {
            addItemDecoration(
                DividerItemDecoration(context, (layoutManager as LinearLayoutManager).orientation)
            )
        }

        lifecycleScope.launchWhenCreated {
            adapter.loadStateFlow.collect { loadStates ->
                binding.swipeRefresh.isRefreshing = loadStates.mediator?.refresh is LoadState.Loading
            }
        }

        lifecycleScope.launchWhenCreated {
            viewModel.schools.collectLatest {
                adapter.submitData(it)
            }
        }

//        lifecycleScope.launchWhenCreated {
//            adapter.loadStateFlow
//                // emit on refresh changes
//                .distinctUntilChangedBy { it.refresh }
//                // when refresh completes (NotLoading)
//                .filter { it.refresh is LoadState.NotLoading }
//                // scroll to top
//                .collect {
//                    val x = "\nrefresh: ${it.refresh} \nmediator: ${it.mediator} \nappend: ${it.append} \nprepend: ${it.prepend}"
//                    Log.d("SchoolListFragment", ">>> scrollToPosition $x")
////                    binding.schoolList.scrollToPosition(0)
//                }
//        }
    }

    private fun initSwipeToRefresh() {
        binding.swipeRefresh.setOnRefreshListener { adapter.refresh() }
    }

    private fun navigateToSchoolSatFragment(school: School) {
        val action = SchoolListFragmentDirections.actionSchoolListFragmentToSchoolSatFragment(school.dbn)
        findNavController().navigate(action)
    }
}