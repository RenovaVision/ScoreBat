package com.renovavision.scorebat.matches.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.renovavision.scorebat.matches.R
import com.renovavision.scorebat.matches.databinding.FragmentMatchDetailsBinding
import com.renovavision.scorebat.common.network.Match
import com.renovavision.scorebat.ui.bindingDelegate
import com.renovavision.scorebat.ui.onViewLifecycle
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import org.koin.androidx.viewmodel.ext.android.stateViewModel

import org.koin.core.parameter.parametersOf

class MatchDetailsFragment : Fragment(R.layout.fragment_match_details) {

    private val binding by bindingDelegate(FragmentMatchDetailsBinding::bind)

    private val model: MatchDetailsViewModel by stateViewModel {
        parametersOf(arguments?.getSerializable("match") as Match)
    }

    @ExperimentalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onViewLifecycle({ binding.toolbar },
            {
                title = context.getString(R.string.match_details)
            })

        lifecycleScope.launchWhenStarted {
            model.state.collect {
                it.html?.let {
                    binding.webView.apply {
                        loadData(it, "text/html", "utf-8")
                        settings.javaScriptEnabled = true
                    }
                }
            }
        }
    }
}