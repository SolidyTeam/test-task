package am.solidy.task.core.base

import am.solidy.coreui.extensions.collectWhenStarted
import am.solidy.task.core.navigation.Command
import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController

abstract class BaseFragment<VM : BaseViewModel>(@LayoutRes layout: Int) : Fragment(layout) {

    abstract val viewModel: VM

    private var navController: NavController? = null
    protected open val navControllerRes: Int? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = getNavController()
        collectWhenStarted(viewModel.command, ::processCommand)
        initView()
        initObservers()
    }

    protected open fun initView() {}
    protected open fun initObservers() {}

    protected open fun processCommand(command: Command) {
        when (command) {
            is Command.FinishAppCommand -> activity?.finishAffinity()
            is Command.NavigateUpCommand -> navController?.popBackStack()
            is Command.NavCommand -> navController?.navigate(command.navDirections)
        }
    }

    private fun getNavController() = try {
        navControllerRes?.let { resId ->
            Navigation.findNavController(requireActivity(), resId)
        } ?: findNavController()
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }

}
