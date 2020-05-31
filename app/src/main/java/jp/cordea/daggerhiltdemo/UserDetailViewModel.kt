package jp.cordea.daggerhiltdemo

import androidx.fragment.app.Fragment
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
class UserDetailViewModel private constructor(
    handle: SavedStateHandle
) : ViewModel() {
    class Factory @Inject constructor(
        private val fragment: Fragment
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
            ): T = UserDetailViewModel(handle) as T
        }
    }

    val title = handle.getLiveData("title", "")
    val phoneNumber = handle.getLiveData("phoneNumber", "")
    val emailAddress = handle.getLiveData("emailAddress", "")
    val jobTitle = handle.getLiveData("jobTitle", "")
    val company = handle.getLiveData("company", "")

    fun init(user: User) {
        title.value = "${user.firstName} ${user.lastName}"
        phoneNumber.value = user.phoneNumber
        emailAddress.value = user.emailAddress
        jobTitle.value = user.title
        company.value = user.company
    }
}
