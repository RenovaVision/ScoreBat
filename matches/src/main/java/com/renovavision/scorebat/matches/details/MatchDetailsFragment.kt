package com.renovavision.scorebat.matches.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.renovavision.scorebat.matches.R
import com.renovavision.scorebat.matches.databinding.FragmentMatchDetailsBinding
import com.renovavision.scorebat.network.Match
import com.renovavision.scorebat.utils.bindingDelegate
import com.renovavision.scorebat.utils.onViewLifecycle
import javax.inject.Inject

class MatchDetailsFragment @Inject constructor() : Fragment() {

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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_match_details, container, false)
}