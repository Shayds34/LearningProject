package com.sederikkuapplication.proteo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.sederikkuapplication.compose.views.Greeting
import com.google.accompanist.appcompattheme.AppCompatTheme
import com.sederikkuapplication.databinding.ActivityProteoMainBinding
import com.sederikkuapplication.proteo.fragments.ProteoMainUiModel
import com.sederikkuapplication.proteo.viewmodel.ProteoMainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProteoMainActivity : AppCompatActivity() {

    companion object {
        fun newInstance(context: Context): Intent {
            return Intent(context, ProteoMainActivity::class.java)
        }
    }

    private var _binding: ActivityProteoMainBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ProteoMainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityProteoMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.greeting.setContent {
            AppCompatTheme {
                Greeting(name = "Shayds")
            }
        }

        binding.btnUnstake.setOnClickListener {
            val intent = Intent(this, ProteoUnstakingActivity::class.java)
            startActivity(intent)
        }

        binding.btnEliteRanking.setOnClickListener {
            val intent = Intent(this, ProteoEliteRankingActivity::class.java)
            startActivity(intent)
        }

        viewModel.viewState.observe(this) {
            when (it.state) {
                ProteoMainUiModel.State.Loading -> {}
                is ProteoMainUiModel.State.Success -> {
                    binding.proteoPrice.text = it.state.proteoPrice
                    binding.proteoIndex.text = it.state.index
                    val url = it.state.token.assets?.pngUrl
                    Glide.with(this).load(url).into(binding.icProteo)
                }
            }
        }

    }

}
