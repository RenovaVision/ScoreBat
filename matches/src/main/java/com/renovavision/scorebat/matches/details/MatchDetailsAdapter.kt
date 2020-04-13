package com.renovavision.scorebat.matches.details

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import com.renovavision.scorebat.matches.databinding.ItemViewVideoBinding
import com.renovavision.scorebat.network.Video
import com.renovavision.scorebat.utils.BaseAdapter
import com.renovavision.scorebat.utils.BaseViewHolder

class MatchDetailsAdapter : BaseAdapter<Video, MatchDetailsAdapter.MatchInfoViewHolder>() {

    override fun buildViewHolder(parent: ViewGroup, viewType: Int): MatchInfoViewHolder {
        return MatchInfoViewHolder(
            ItemViewVideoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun areItemsTheSame(oldItem: Video, newItem: Video) =
        oldItem.embed == newItem.embed

    inner class MatchInfoViewHolder(private val binding: ItemViewVideoBinding) :
        BaseViewHolder<Video>(binding.root) {

        @SuppressLint("SetJavaScriptEnabled")
        override fun onBind(item: Video) {
            binding.webView.apply {
                loadData(item.embed, "text/html", "utf-8")
                settings.javaScriptEnabled = true
            }
        }
    }
}