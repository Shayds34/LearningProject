package com.sederikkuapplication.projects.proteo.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.sederikkuapplication.databinding.FragmentUnstakingBinding
import com.sederikkuapplication.projects.proteo.adapters.TransactionListAdapter
import com.sederikkuapplication.projects.proteo.viewmodel.ProteoUnstakingViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class UnstakingFragment : Fragment() {

    // This property is only valid between onCreateView and
    // onDestroyView.
    private var _binding: FragmentUnstakingBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ProteoUnstakingViewModel by viewModels()

    private val transactionListAdapter by lazy { TransactionListAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUnstakingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configure()
    }

    private fun configure() {
        configureFragment()
    }

    private fun configureFragment() {
        binding.fragmentRecyclerView.apply {
            layoutManager = context?.let { context -> LinearLayoutManager(context) }
            adapter = transactionListAdapter
        }

        transactionListAdapter.onItemClicked = { transactionModel ->
            context?.let { context ->
                viewModel.onCardClicked(transactionModel, context)
            }
        }

        binding.fragmentButtonPrevious.setOnClickListener {
            viewModel.viewModelScope.launch {
                viewModel.onPreviousClick()
            }
        }

        binding.fragmentButtonNext.setOnClickListener {
            viewModel.viewModelScope.launch {
                viewModel.onNextClick()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
