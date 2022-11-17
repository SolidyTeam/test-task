package am.solidy.coreui.extensions

import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import me.ibrahimyilmaz.kiel.core.RecyclerViewHolder

fun RecyclerViewHolder<Any>.getString(@StringRes resId: Int, vararg args: Any): String =
    itemView.context.getString(resId, *args)

fun RecyclerViewHolder<Any>.getDrawable(@DrawableRes resId: Int) =
    ContextCompat.getDrawable(itemView.context, resId)

fun RecyclerViewHolder<Any>.getColor(@ColorRes resId: Int) =
    ContextCompat.getColor(itemView.context, resId)

fun RecyclerViewHolder<Any>.getColorStateList(@ColorRes resId: Int) =
    ContextCompat.getColorStateList(itemView.context, resId)

fun RecyclerViewHolder<Any>.getDimen(@DimenRes resId: Int) =
    itemView.resources.getDimension(resId)

fun RecyclerViewHolder<Any>.getDimenPixelSize(@DimenRes resId: Int) =
    itemView.resources.getDimensionPixelSize(resId)