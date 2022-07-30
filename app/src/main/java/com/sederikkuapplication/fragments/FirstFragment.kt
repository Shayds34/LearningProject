package com.sederikkuapplication.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.sederikkuapplication.databinding.FragmentFirstBinding
import com.sederikkuapplication.network.MainRepository
import com.sederikkuapplication.network.Service
import kotlinx.coroutines.launch

class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var viewModel: FirstViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val service = Service.getInstance()
        val repository = MainRepository(service)

        viewModel = ViewModelProvider(
            this,
            FirstViewModelFactory(repository)
        ).get(FirstViewModel::class.java)

        binding.buttonStart.setOnClickListener {
            viewModel.viewModelScope.launch {
                viewModel.onClick()
            }
        }

        // Observe chaque changement de tokensList
        viewModel.tokensList.observe(viewLifecycleOwner) { list ->
            list.map { token ->

                // Simple log pour afficher la réponse dans Logcat
                Log.d("Tokens", "${token.address} - ${token.balance}")
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
