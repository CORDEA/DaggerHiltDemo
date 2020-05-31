package jp.cordea.daggerhiltdemo

import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@ExperimentalCoroutinesApi
class UsersViewModel private constructor(
    handle: SavedStateHandle,
    repository: UserRepository
) : ViewModel(), UserListItemViewModel.OnClickListener {
    class Factory @Inject constructor(
        private val fragment: Fragment,
        private val repository: UserRepository
    ) {
        fun create() = object : AbstractSavedStateViewModelFactory(
            fragment,
            fragment.arguments
        ) {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel?> create(
                key: String,
                modelClass: Class<T>,
                handle: SavedStateHandle
            ): T = UsersViewModel(handle, repository) as T
        }
    }

    private val _items = handle.getLiveData<List<UserListItemViewModel>>("items")
    val items: LiveData<List<UserListItemViewModel>> get() = _items

    init {
        repository
            .findAll(true)
            .map { users -> users.map { UserListItemViewModel(it) } }
            .flowOn(Dispatchers.IO)
            .onEach { _items.value = it }
            .launchIn(viewModelScope)
    }

    override fun onItemClick(user: User) {
    }
}
