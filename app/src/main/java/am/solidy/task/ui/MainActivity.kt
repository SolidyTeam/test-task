package am.solidy.task.ui

import am.solidy.task.R
import android.os.Bundle
import android.text.Layout
import android.text.Spannable
import android.text.SpannableString
import android.text.style.AlignmentSpan
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel.showErrorMessage.flowWithLifecycle(lifecycle)
            .onEach { message->
                val centeredText = SpannableString(message)
                centeredText.setSpan(
                    AlignmentSpan.Standard(Layout.Alignment.ALIGN_CENTER),
                    0, message.length - 1,
                    Spannable.SPAN_INCLUSIVE_INCLUSIVE
                )
                Toast.makeText(this, centeredText, Toast.LENGTH_SHORT).show()
            }
            .launchIn(lifecycleScope)
    }
}