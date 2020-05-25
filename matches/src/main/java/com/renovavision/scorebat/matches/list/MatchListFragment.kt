package com.renovavision.scorebat.matches.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import com.renovavision.scorebat.matches.R
import com.renovavision.scorebat.matches.databinding.FragmentMatchesListBinding
import com.renovavision.scorebat.ui.bindingDelegate
import com.renovavision.scorebat.ui.observe
import com.renovavision.scorebat.ui.onViewLifecycle
import org.koin.androidx.viewmodel.ext.android.viewModel

class MatchListFragment : Fragment(R.layout.fragment_matches_list) {

    private val matchesAdapter = MatchesAdapter { model.dispatch(it) }

    private val binding by bindingDelegate(FragmentMatchesListBinding::bind)

    private val model: MatchListViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_matches_list, container, false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        onViewLifecycle({ binding.toolbar },
            {
                title = context.getString(R.string.matches)
            })
        onViewLifecycle({ binding.recyclerView },
            {
                layoutManager = LinearLayoutManager(context, VERTICAL, false)
                adapter = matchesAdapter
            })
        onViewLifecycle({ binding.retryButton },
            {
                setOnClickListener { model.dispatch(LoadMatches) }
            }, {
                setOnClickListener(null)
            })

        model.dispatch(LoadMatches)
    }

    override fun onStart() {
        super.onStart()
        model.networkState.observe(this) {
            matchesAdapter.updateItems(it.matches)
            binding.recyclerView.visibility = if (!it.showError) View.VISIBLE else View.GONE
            binding.errorContainer.visibility = if (it.showError) View.VISIBLE else View.GONE
            binding.progress.visibility = if (it.isLoading) View.VISIBLE else View.GONE
        }
    }
}