package com.sederikkuapplication.projects.proteo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.sederikkuapplication.databinding.ActivityProteoEliteRankingBinding
import com.sederikkuapplication.projects.proteo.adapters.ProteoEliteRankingAdapter
import com.sederikkuapplication.projects.proteo.fragments.EliteRankingUiModel
import com.sederikkuapplication.projects.proteo.viewmodel.ProteoEliteRankingViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProteoEliteRankingActivity : AppCompatActivity() {

    companion object {
        fun newInstance(context: Context): Intent {
            return Intent(context, ProteoEliteRankingActivity::class.java)
        }
    }

    private var _binding: ActivityProteoEliteRankingBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ProteoEliteRankingViewModel by viewModels()

    private val eliteAdapter by lazy { ProteoEliteRankingAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityProteoEliteRankingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        binding.eliteRankingRecyclerView.apply {
            layoutManager = context?.let { context -> LinearLayoutManager(context) }
            adapter = eliteAdapter
        }

        eliteAdapter.onItemClicked = {
            viewModel.onCardClicked(it, this)
        }

        viewModel.viewState.observe(this) {
            when (it.state) {
                EliteRankingUiModel.State.Loading -> {
                    // unused
                }
                is EliteRankingUiModel.State.Success -> {
                    eliteAdapter.setItems(it.state.adapterItems)
                }
            }
        }

    }

}