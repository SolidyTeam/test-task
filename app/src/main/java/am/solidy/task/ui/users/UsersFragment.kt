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
            viewModel.getUsers()
        }
    }

    override fun initObservers() {
        collectWhenStarted(viewModel.users) { users ->
            users?.let {
                val isLoading = users.isEmpty()
                adapter.submitList(users){
                    binding.srUsers.isRefreshing = false
                }
                with(binding) {
                    tvTitle.isVisible = !isLoading
                    srUsers.isVisible = !isLoading
                    vLoading.root.isVisible = isLoading
                }
            }
        }
    }

    private fun createAdapter() = UsersAdapter(
        viewModel::navigateToUserDetails
    ).adapter

}