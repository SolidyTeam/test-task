package am.solidy.task.ui.users

import am.solidy.coreui.delegate.viewBinding
import am.solidy.coreui.extensions.collectWhenStarted
import am.solidy.task.R
import am.solidy.task.core.base.BaseFragment
import am.solidy.task.databinding.FragmentUsersBinding
import am.solidy.task.ui.users.adapter.UsersAdapter
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UsersFragment : BaseFragment<UsersViewModel>(R.layout.fragment_users) {

    override val viewModel: UsersViewModel by viewModels()
    private val binding by viewBinding(FragmentUsersBinding::bind)

    private val adapter by lazy { createAdapter() }

    override fun initView() = with(binding) {
        rvUsers.layoutManager = LinearLayoutManager(context)
        rvUsers.adapter = adapter
        srUsers.setOnRefreshListener {
            viewModel.fetchData()
        }
    }

    override fun initObservers() {
        collectWhenStarted(viewModel.users) { users ->
            adapter.submitList(users)
        }
        collectWhenStarted(viewModel.isLoading) { isLoading ->
            with(binding) {
                tvTitle.isVisible = !isLoading
                rvUsers.isVisible = !isLoading
                tvErrorMessage.isVisible = !isLoading
                if (!isLoading) srUsers.isRefreshing = false
                if (!srUsers.isRefreshing) vLoading.root.isVisible = isLoading
            }
        }
        collectWhenStarted(viewModel.showErrorMessage) { message ->
            val shouldShowError = message.isNotEmpty()
            with(binding) {
                tvTitle.isVisible = !shouldShowError
                rvUsers.isVisible = !shouldShowError
                tvErrorMessage.isVisible = shouldShowError
                tvErrorMessage.text = message
            }
        }
    }

    private fun createAdapter() = UsersAdapter(
        viewModel::navigateToUserDetails
    ).adapter

}