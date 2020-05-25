package com.renovavision.scorebat.matches.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import com.renovavision.scorebat.matches.R
import com.renovavision.scorebat.matches.databinding.FragmentMatchesListBinding
import com.renovavision.scorebat.ui.bindingDelegate
import com.renovavision.scorebat.ui.onViewLifecycle
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
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

    @ExperimentalCoroutinesApi
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

        lifecycleScope.launchWhenStarted {
            model.state.collect {
                matchesAdapter.updateItems(it.matches)
                binding.recyclerView.visibility = if (!it.showError) View.VISIBLE else View.GONE
                binding.errorContainer.visibility = if (it.showError) View.VISIBLE else View.GONE
                binding.progress.visibility = if (it.isLoading) View.VISIBLE else View.GONE
            }
        }

        model.dispatch(LoadMatches)
    }
}