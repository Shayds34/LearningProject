package com.sederikkuapplication.proteo.adapters

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sederikkuapplication.proteo.model.EliteDetailedModelUi

open class ProteoEliteRankingAdapter :
    RecyclerView.Adapter<ProteoEliteRankingViewHolder>() {

    private var items = emptyList<EliteDetailedModelUi>()

    @SuppressLint("NotifyDataSetChanged")
    internal fun setItems(items: List<EliteDetailedModelUi>) {
        this.items = items
        notifyDataSetChanged()
    }

    var onItemClicked: ((EliteDetailedModelUi) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProteoEliteRankingViewHolder {
        return ProteoEliteRankingViewHolder.newInstance(
            parent = parent,
            onClickListener = {
                onItemClicked?.invoke(it)
            }
        )
    }

    override fun onBindViewHolder(holder: ProteoEliteRankingViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return items.size
    }

}