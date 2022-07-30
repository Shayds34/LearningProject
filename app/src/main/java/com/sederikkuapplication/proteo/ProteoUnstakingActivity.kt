package com.sederikkuapplication.proteo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sederikkuapplication.databinding.ActivityProteoUnstakingBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProteoUnstakingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProteoUnstakingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityProteoUnstakingBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}