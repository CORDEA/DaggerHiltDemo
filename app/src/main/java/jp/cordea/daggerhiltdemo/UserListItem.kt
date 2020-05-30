package jp.cordea.daggerhiltdemo

import android.view.View
import com.xwray.groupie.viewbinding.BindableItem
import jp.cordea.daggerhiltdemo.databinding.UserListItemBinding

class UserListItemViewModel(
    firstName: String,
    lastName: String,
    val title: String
) {
    companion object {
        fun from(user: User) =
            UserListItemViewModel(user.firstName, user.lastName, user.title)
    }

    val name = "$firstName $lastName"
}

class UserListItem(
    private val viewModel: UserListItemViewModel
) : BindableItem<UserListItemBinding>() {
    override fun initializeViewBinding(view: View): UserListItemBinding =
        UserListItemBinding.bind(view)

    override fun getLayout(): Int = R.layout.user_list_item

    override fun bind(viewBinding: UserListItemBinding, position: Int) {
        viewBinding.viewModel = viewModel
    }
}
