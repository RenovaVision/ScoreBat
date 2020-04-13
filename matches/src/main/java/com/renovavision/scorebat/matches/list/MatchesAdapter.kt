package com.renovavision.scorebat.matches.list

import android.view.LayoutInflater
import android.view.ViewGroup
import com.renovavision.scorebat.matches.databinding.ItemViewMatchBinding
import com.renovavision.scorebat.network.Match
import com.renovavision.scorebat.utils.BaseAdapter
import com.renovavision.scorebat.utils.BaseViewHolder
import com.renovavision.scorebat.utils.Dispatch
import com.squareup.picasso.Picasso

class MatchesAdapter(dispatch: Dispatch) :
    BaseAdapter<Match, MatchesAdapter.MatchViewHolder>(dispatch) {

    override fun buildViewHolder(parent: ViewGroup, viewType: Int) = MatchViewHolder(
        ItemViewMatchBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun areItemsTheSame(oldItem: Match, newItem: Match) =
        oldItem.title == newItem.title && oldItem.date == oldItem.date

    inner class MatchViewHolder(private val binding: ItemViewMatchBinding) :
        BaseViewHolder<Match>(binding.root) {

        override fun onCreate(dispatch: Dispatch) {
            super.onCreate(dispatch)
            itemView.setOnClickListener {
                item.let { dispatch.invoke(MatchClicked(item)) }
            }
        }

        override fun onBind(item: Match) {
            Picasso.get()
                .load(item.thumbnail)
                .into(binding.matchPoster)
        }
    }
}