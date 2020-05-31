package jp.cordea.daggerhiltdemo

import android.os.Parcelable
import android.view.View
import com.xwray.groupie.viewbinding.BindableItem
import jp.cordea.daggerhiltdemo.databinding.UserListItemBinding
import kotlinx.android.parcel.Parcelize

@Parcelize
class UserListItemViewModel(
    val user: User
) : Parcelable {
    interface OnClickListener {
        fun onItemClick(user: User)
    }

    val name get() = "${user.firstName} ${user.lastName}"
    val title get() = user.title
}

class UserListItem(
    private val listener: UserListItemViewModel.OnClickListener,
    private val viewModel: UserListItemViewModel
) : BindableItem<UserListItemBinding>() {
    override fun initializeViewBinding(view: View): UserListItemBinding =
        UserListItemBinding.bind(view)

    override fun getLayout(): Int = R.layout.user_list_item

    override fun bind(viewBinding: UserListItemBinding, position: Int) {
        viewBinding.viewModel = viewModel
        viewBinding.root.setOnClickListener {
            listener.onItemClick(viewModel.user)
        }
    }
}
