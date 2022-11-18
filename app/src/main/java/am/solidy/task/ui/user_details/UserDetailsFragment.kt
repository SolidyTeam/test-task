package am.solidy.task.ui.user_details

import am.solidy.coreui.delegate.viewBinding
import am.solidy.coreui.extensions.collectWhenStarted
import am.solidy.task.R
import am.solidy.task.core.base.BaseFragment
import am.solidy.task.databinding.FragmentUserDetailsBinding
import am.solidy.task.ui.user_details.adapter.UserDetailsAdapter
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserDetailsFragment : BaseFragment<UserDetailsViewModel>(R.layout.fragment_user_details) {

    override val viewModel: UserDetailsViewModel by viewModels()
    private val binding by viewBinding(FragmentUserDetailsBinding::bind)

    private val adapter by lazy { createAdapter() }

    override fun initView() = with(binding) {
        rvUserDetails.layoutManager = LinearLayoutManager(context)
        rvUserDetails.adapter = adapter
        toolbar.ivBack.setOnClickListener { viewModel.navigateUp() }
    }

    override fun initObservers() {
        collectWhenStarted(viewModel.userDetails) { userDetails ->
            adapter.submitList(userDetails)
        }
    }

    private fun createAdapter() = UserDetailsAdapter().adapter

}