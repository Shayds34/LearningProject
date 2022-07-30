package com.sederikkuapplication.proteo.adapters

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sederikkuapplication.proteo.model.TransactionModelUi

open class TransactionListAdapter :
    RecyclerView.Adapter<TransactionListViewHolder>() {

    private var items = emptyList<TransactionModelUi>()

    @SuppressLint("NotifyDataSetChanged")
    internal fun setItems(items: List<TransactionModelUi>) {
        this.items = items
        notifyDataSetChanged()
    }

    var onItemClicked: ((TransactionModelUi) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionListViewHolder {
        return TransactionListViewHolder.newInstance(
            parent = parent,
            onClickListener = {
                onItemClicked?.invoke(it)
            }
        )
    }

    override fun onBindViewHolder(holder: TransactionListViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return items.size
    }
}