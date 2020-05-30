package jp.cordea.daggerhiltdemo

import android.view.View
import com.xwray.groupie.viewbinding.BindableItem
import jp.cordea.daggerhiltdemo.databinding.UserListItemBinding

class UserListItemViewModel()

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
