package am.solidy.task.ui.main

import am.solidy.coreui.delegate.viewBinding
import am.solidy.task.R
import am.solidy.task.core.base.BaseFragment
import am.solidy.task.databinding.FragmentMainBinding
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : BaseFragment<MainViewModel>(R.layout.fragment_main) {

    override val viewModel: MainViewModel by viewModels()
    private val binding by viewBinding(FragmentMainBinding::bind)

}