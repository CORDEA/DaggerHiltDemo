package jp.cordea.daggerhiltdemo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import dagger.hilt.android.AndroidEntryPoint
import jp.cordea.daggerhiltdemo.databinding.UsersFragmentBinding
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@AndroidEntryPoint
@ExperimentalCoroutinesApi
class UsersFragment : Fragment() {
    @Inject
    lateinit var factory: UsersViewModel.Factory

    private val viewModel by viewModels<UsersViewModel> { factory.create() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = UsersFragmentBinding.inflate(inflater, container, false)
        val adapter = GroupAdapter<GroupieViewHolder>()
        binding.recyclerView.adapter = adapter
        viewModel.items.observe(viewLifecycleOwner, Observer { items ->
            adapter.update(items.map { UserListItem(viewModel, it) })
        })
        viewModel.events.observe(viewLifecycleOwner, Observer { event ->
            event.poll()?.let {
                when (it) {
                    is UsersViewModel.Event.ClickedItem ->
                        findNavController().navigate(
                            UsersFragmentDirections
                                .actionUsersFragmentToUserDetailFragment(it.user)
                        )
                }
            }
        })
        return binding.root
    }
}
