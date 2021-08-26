package com.sederikkuapplication.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.edit
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.sederikkuapplication.R
import com.sederikkuapplication.databinding.FragmentFirstBinding

class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Load username if it's been saved.
        // Otherwise, it's null.
        loadName().let {
            binding.editTextName?.setText(it)
        }

        binding.buttonStart.setOnClickListener {
            binding.editTextName?.text.let { name ->
                if (name.toString().isNotEmpty()) {
                    saveName(name.toString())
                    findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
                } else {
                    Toast.makeText(
                        context,
                        "Vous devez renseigner un nom pour continuer.",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun saveName(name: String) {
        val sharedPreferences =
            requireActivity().getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE)
        sharedPreferences.edit {
            putString(KEY_NAME, name)
            apply()
        }
    }

    private fun loadName(): String? {
        val sharedPreferences =
            requireActivity().getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE)
        return sharedPreferences.getString(KEY_NAME, null)
    }

    companion object {
        const val SHARED_PREF = "sharedPrefs"
        const val KEY_NAME = "name"
    }

}
