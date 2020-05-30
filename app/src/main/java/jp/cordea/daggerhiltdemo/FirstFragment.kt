package jp.cordea.daggerhiltdemo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import dagger.hilt.android.AndroidEntryPoint
import jp.cordea.daggerhiltdemo.databinding.FragmentFirstBinding
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@AndroidEntryPoint
@ExperimentalCoroutinesApi
class FirstFragment : Fragment() {
    @Inject
    lateinit var factory: FirstViewModel.Factory

    private val viewModel by viewModels<FirstViewModel> { factory.create() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentFirstBinding.inflate(inflater, container, false)
        val adapter = GroupAdapter<GroupieViewHolder>()
        binding.recyclerView.adapter = adapter
        viewModel.items.observe(viewLifecycleOwner, Observer { items ->
            adapter.update(items.map { UserListItem(UserListItemViewModel.from(it)) })
        })
        return binding.root
    }
}
