package com.sederikkuapplication.projects.proteo.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sederikkuapplication.databinding.ItemUnstakingBinding
import com.sederikkuapplication.projects.proteo.model.TransactionModelUi

open class TransactionListViewHolder(
    val context: Context,
    val binding: ItemUnstakingBinding,
    val onClickListener: (TransactionModelUi) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun newInstance(
            parent: ViewGroup,
            onClickListener: (TransactionModelUi) -> Unit
        ) = TransactionListViewHolder(
            context = parent.context,
            binding = ItemUnstakingBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            onClickListener = onClickListener
        )
    }

    private var currentAdapterItem: (TransactionModelUi)? = null

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

    fun bind(adapterItem: TransactionModelUi) {
        this.currentAdapterItem = adapterItem

        binding.apply {
            fragmentAddress.text = adapterItem.data.shortenErd
            fragmentAmount.text = adapterItem.data.token
            fragmentEpoch.text = adapterItem.data.epoch
            fragmentDuration.text = adapterItem.data.function
        }
    }

}