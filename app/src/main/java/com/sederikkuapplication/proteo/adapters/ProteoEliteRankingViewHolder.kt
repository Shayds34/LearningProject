package com.sederikkuapplication.proteo.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sederikkuapplication.databinding.ItemEliteRankingBinding
import com.sederikkuapplication.proteo.model.EliteDetailedModelUi

class ProteoEliteRankingViewHolder (
    val context: Context,
    val binding: ItemEliteRankingBinding,
    val onClickListener: (EliteDetailedModelUi) -> Unit
) : RecyclerView.ViewHolder(binding.root){

    companion object {
        fun newInstance(
            parent: ViewGroup,
            onClickListener: (EliteDetailedModelUi) -> Unit
        ) = ProteoEliteRankingViewHolder(
            context = parent.context,
            binding = ItemEliteRankingBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            onClickListener = onClickListener
        )
    }

    private var currentAdapterItem: (EliteDetailedModelUi)? = null

    private val clickListener: ((View) -> Unit) = {
        currentAdapterItem?.let {
            onClickListener(it)
        }
    }

    init {
        binding.listItemContainer.setOnClickListener {
            clickListener(it)
        }
    }

    fun bind(adapterItem: EliteDetailedModelUi) {
        this.currentAdapterItem = adapterItem

        binding.apply {
            fragmentRank.text = (adapterPosition + 1).toString()
            fragmentAddress.text = adapterItem.data.shorten
            fragmentBalance.text = adapterItem.data.balance
        }
    }

}
