package com.renovavision.scorebat.utils

import androidx.recyclerview.widget.DiffUtil

abstract class BaseDiffUtilCallback<T>(
    private val oldList: List<T>,
    private val newList: List<T>
) : DiffUtil.Callback() {

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    abstract override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition].hashCode() == newList[newItemPosition].hashCode()
}