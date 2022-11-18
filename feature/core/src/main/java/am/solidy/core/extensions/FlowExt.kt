package am.solidy.core.extensions

import am.solidy.core.delegate.Event
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach

fun Flow<Event>.onEachMessageEvent(block: suspend (Event.MessageEvent) -> Unit) = onEach {
    if (it is Event.MessageEvent) block(it)
}