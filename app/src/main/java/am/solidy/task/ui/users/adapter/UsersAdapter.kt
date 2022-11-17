package am.solidy.task.ui.users.adapter

import am.solidy.core.entity.UserWithPostsEntity
import am.solidy.coreui.extensions.getColor
import am.solidy.coreui.extensions.getString
import am.solidy.task.R
import am.solidy.task.databinding.ItemUserBinding
import am.solidy.task.utils.RColor
import am.solidy.task.utils.RString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.view.View
import androidx.core.text.buildSpannedString
import androidx.core.text.toSpannable
import coil.load
import coil.transform.CircleCropTransformation
import me.ibrahimyilmaz.kiel.adapterOf
import me.ibrahimyilmaz.kiel.core.RecyclerViewHolder

class UsersAdapter(
    private val onUserClick: (UserWithPostsEntity) -> Unit
) {

    val adapter = adapterOf<UserWithPostsEntity> {
        diff(
            areItemsTheSame = { old, new ->
                old.user.id == new.user.id
            },
            areContentsTheSame = { old, new ->
                old.user == new.user
            }
        )
        register(
            viewHolder = { v -> ViewHolder(v) },
            layoutResource = R.layout.item_user,
            onBindViewHolder = { vh, _, p -> vh.bind(p) }
        )
    }

    private inner class ViewHolder(
        view: View
    ) : RecyclerViewHolder<UserWithPostsEntity>(view) {

        private val binding = ItemUserBinding.bind(view)

        fun bind(item: UserWithPostsEntity) {
            val postCount = getPostCount(item.posts.size)
            with(binding) {
                ivProfPic.load(item.user.thumbnailUrl) {
                    transformations(CircleCropTransformation())
                }
                tvUsername.text = item.user.name
                tvPostCount.text = postCount
                root.setOnClickListener { onUserClick(item) }
            }
        }

        private fun getPostCount(count: Int) = buildSpannedString {
            append(getString(RString.users_post_count))
            val spannedCount = count.toString().toSpannable().apply {
                setSpan(
                    ForegroundColorSpan(getColor(RColor.color_gray)),
                    0, length, Spanned.SPAN_EXCLUSIVE_INCLUSIVE
                )
            }
            append(" ")
            append(spannedCount)
        }

    }

}