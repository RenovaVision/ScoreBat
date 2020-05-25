package com.renovavision.scorebat.matches.list

import android.view.LayoutInflater
import android.view.ViewGroup
import com.renovavision.scorebat.matches.databinding.ItemViewMatchBinding
import com.renovavision.scorebat.common.network.Match
import com.renovavision.scorebat.ui.BaseAdapter
import com.renovavision.scorebat.ui.BaseViewHolder
import com.renovavision.scorebat.ui.EventHandler
import com.squareup.picasso.Picasso

class MatchesAdapter(eventHandler: EventHandler) :
    BaseAdapter<Match, MatchesAdapter.MatchViewHolder>(eventHandler) {

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

        override fun onCreate(eventHandler: EventHandler) {
            super.onCreate(eventHandler)
            itemView.setOnClickListener {
                item.let { eventHandler.invoke(MatchClicked(item)) }
            }
        }

        override fun onBind(item: Match) {
            Picasso.get()
                .load(item.thumbnail)
                .into(binding.matchPoster)
        }
    }
}