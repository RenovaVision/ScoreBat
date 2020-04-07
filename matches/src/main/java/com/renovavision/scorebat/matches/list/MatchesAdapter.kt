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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchViewHolder {
        val binding =
            ItemViewMatchBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return MatchViewHolder(binding)
    }

    private var listener: View.OnClickListener? = null

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
            oldList[oldItemPosition].date == newList[newItemPosition].date
    }
}