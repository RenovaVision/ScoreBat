package com.renovavision.scorebat.matches.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import com.renovavision.scorebat.matches.R
import com.renovavision.scorebat.matches.databinding.FragmentMatchesListBinding
import com.renovavision.scorebat.network.model.Match
import com.renovavision.scorebat.utils.bindingDelegate
import com.renovavision.scorebat.utils.observe
import com.renovavision.scorebat.utils.onViewLifecycle
import javax.inject.Inject
import javax.inject.Named

class MatchListFragment @Inject constructor(
    private val viewModelFactory: ViewModelProvider.Factory,
    @Named("navMatchesListToMatchDetails")
    private val navMatchesListToMatchDetails: (match: @JvmSuppressWildcards Match) -> Unit
) : Fragment() {

    private lateinit var matchesAdapter: MatchesAdapter

    private val binding by bindingDelegate(FragmentMatchesListBinding::bind)

    private val viewModel: MatchListViewModel by viewModels { viewModelFactory }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_matches_list, container, false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        matchesAdapter = MatchesAdapter()

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
                setOnClickListener { viewModel.loadMatches() }
            }, {
                setOnClickListener(null)
            })

        viewModel.loadMatches()
    }

    override fun onStart() {
        super.onStart()

        viewModel.networkState.observe(this) {
            matchesAdapter.updateItems(it.matches)
            binding.recyclerView.visibility = if (!it.showError) View.VISIBLE else View.GONE
            binding.errorContainer.visibility = if (it.showError) View.VISIBLE else View.GONE
            binding.progress.visibility = if (it.isLoading) View.VISIBLE else View.GONE
        }
    }
}