package com.renovavision.scorebat.matches.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.renovavision.scorebat.matches.databinding.ItemViewMatchBinding
import com.renovavision.scorebat.network.model.Match
import com.renovavision.scorebat.utils.BaseAdapter
import com.renovavision.scorebat.utils.BaseDiffUtilCallback
import com.renovavision.scorebat.utils.BaseViewHolder
import com.squareup.picasso.Picasso

class MatchesAdapter : BaseAdapter<Match, MatchesAdapter.MatchViewHolder>() {

    var listener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchViewHolder {
        val holder = MatchViewHolder(
            ItemViewMatchBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

        holder.itemView.setOnClickListener {
            listener?.onItemClicked(items[holder.adapterPosition])
        }

        return holder
    }

    override fun updateItems(list: List<Match>) {
        val diffResult = DiffUtil.calculateDiff(DiffUtilCallback(items, list))

        items = list
        diffResult.dispatchUpdatesTo(this)
    }

    inner class MatchViewHolder(private val binding: ItemViewMatchBinding) :
        BaseViewHolder<Match>(binding.root) {

        override fun bind(item: Match) {
            Picasso.get()
                .load(item.thumbnail)
                .into(binding.matchPoster)
        }
    }

    inner class DiffUtilCallback(
        private val oldList: List<Match>,
        private val newList: List<Match>
    ) : BaseDiffUtilCallback<Match>(oldList, newList) {

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
            oldList[oldItemPosition].title == newList[newItemPosition].title &&
                    oldList[oldItemPosition].date == newList[newItemPosition].date
    }

    interface OnItemClickListener {

        fun onItemClicked(match: Match)
    }
}