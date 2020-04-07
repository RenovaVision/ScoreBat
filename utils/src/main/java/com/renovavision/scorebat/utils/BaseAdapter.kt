package com.renovavision.scorebat.utils

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<T, VH : BaseViewHolder<T>> : RecyclerView.Adapter<VH>() {

    var items: List<T> = emptyList()

    abstract override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH

    override fun onBindViewHolder(holder: VH, position: Int){
        val item = items[position]
        holder.bind(item)
    }

    abstract fun updateItems(list: List<T>)

    override fun getItemCount() = items.size
}