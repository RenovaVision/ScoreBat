package com.renovavision.scorebat.matches.details

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import com.renovavision.scorebat.matches.BuildConfig
import com.renovavision.scorebat.matches.databinding.ItemViewVideoBinding
import com.renovavision.scorebat.network.model.Video
import com.renovavision.scorebat.utils.BaseAdapter
import com.renovavision.scorebat.utils.BaseViewHolder

class MatchDetailsAdapter : BaseAdapter<Video, MatchDetailsAdapter.MatchInfoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchInfoViewHolder {
        return MatchInfoViewHolder(
            ItemViewVideoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun updateItems(list: List<Video>) {
        items = list
        notifyDataSetChanged()
    }

    inner class MatchInfoViewHolder(private val binding: ItemViewVideoBinding) :
        BaseViewHolder<Video>(binding.root) {

        @SuppressLint("SetJavaScriptEnabled")
        override fun bind(item: Video) {
            binding.webView.apply {
                loadData(item.embed, "text/html", "utf-8")
                settings.javaScriptEnabled = true
            }
        }
    }
}