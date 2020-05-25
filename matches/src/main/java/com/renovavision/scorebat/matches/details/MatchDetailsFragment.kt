package com.renovavision.scorebat.matches.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.renovavision.scorebat.matches.R
import com.renovavision.scorebat.matches.databinding.FragmentMatchDetailsBinding
import com.renovavision.scorebat.common.network.Match
import com.renovavision.scorebat.ui.bindingDelegate
import com.renovavision.scorebat.ui.onViewLifecycle

class MatchDetailsFragment : Fragment(R.layout.fragment_match_details) {

    private val binding by bindingDelegate(FragmentMatchDetailsBinding::bind)

    private val matchDetailsAdapter = MatchDetailsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val match = arguments?.getSerializable("match") as Match

        onViewLifecycle({ binding.toolbar },
            {
                title = context.getString(R.string.match_details)
            })

        val itemDecoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)

        onViewLifecycle({ binding.recyclerView },
            {
                layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
                addItemDecoration(itemDecoration)
                matchDetailsAdapter.updateItems(match.videos)
                adapter = matchDetailsAdapter
            }, { removeItemDecoration(itemDecoration) })
    }
}