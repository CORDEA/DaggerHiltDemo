package jp.cordea.daggerhiltdemo

import androidx.fragment.app.Fragment
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class FirstViewModel private constructor(
    private val handle: SavedStateHandle,
    private val repository: UserRepository
) : ViewModel() {
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
            ): T = FirstViewModel(handle, repository) as T
        }
    }
}
