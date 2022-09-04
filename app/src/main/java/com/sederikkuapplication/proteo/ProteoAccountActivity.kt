package com.sederikkuapplication.proteo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.sederikkuapplication.databinding.ActivityProteoAccountBinding
import com.sederikkuapplication.proteo.fragments.AccountUiModel
import com.sederikkuapplication.proteo.viewmodel.ProteoAccountViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProteoAccountActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_ADDRESS = "EXTRA_ADDRESS"
        fun newInstance(
            context: Context,
            address: String?
        ): Intent {
            return Intent(context, ProteoAccountActivity::class.java).apply {
                putExtra(EXTRA_ADDRESS, address)
            }
        }
    }

    private var _binding: ActivityProteoAccountBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ProteoAccountViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityProteoAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        viewModel.viewState.observe(this) {
            when (it.state) {
                AccountUiModel.State.Loading -> {}
                is AccountUiModel.State.Success -> {
                    binding.walletText.text = it.state.accountDetailed.address
                    binding.herotagText.text = it.state.accountDetailed.username
                    binding.proteoInWallet.text = it.state.proteoInWallet
                    binding.sproteoInWallet.text = it.state.sProteoInWallet
                    binding.eliteBalanceAmount.text = it.state.eliteBalance
                    binding.egldInFarm.text = it.state.egldInStaking
                    binding.usdcInFarm.text = it.state.usdcInStaking
                }
            }
        }
    }
}
