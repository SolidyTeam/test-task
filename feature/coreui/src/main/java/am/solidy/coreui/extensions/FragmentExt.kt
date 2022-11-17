package am.solidy.coreui.extensions

import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.annotation.DrawableRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

fun <T> Fragment.collectWhenStarted(flow: Flow<T>, block: suspend (value: T) -> Unit) =
    flow.flowWithLifecycle(lifecycle)
        .onEach(block)
        .launchIn(viewLifecycleOwner.lifecycleScope)

fun <T> Fragment.collectLatestWhenStarted(flow: Flow<T>, block: suspend (value: T) -> Unit) {
    viewLifecycleOwner.lifecycleScope.launch {
        flow.flowWithLifecycle(lifecycle).collectLatest { block(it) }
    }
}

fun Fragment.getColor(@ColorRes resId: Int) = requireContext().getColorCompat(resId)

fun Fragment.getDimen(@DimenRes resId: Int) = requireContext().getDimen(resId)

fun Fragment.getDrawable(@DrawableRes resId: Int) = requireContext().getDrawableCompat(resId)
