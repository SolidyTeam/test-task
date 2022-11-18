package am.solidy.task.ui.user_details.adapter

import am.solidy.task.R
import am.solidy.task.databinding.ItemUserDetailsBinding
import am.solidy.task.databinding.ItemUserPostBinding
import android.view.View
import coil.load
import coil.transform.CircleCropTransformation
import me.ibrahimyilmaz.kiel.adapterOf
import me.ibrahimyilmaz.kiel.core.RecyclerViewHolder

class UserDetailsAdapter {

    val adapter = adapterOf<UserDetailItem> {
        diff(
            areItemsTheSame = { old, new ->
                old.id == new.id
            },
            areContentsTheSame = { old, new ->
                old == new
            }
        )
        register(
            viewHolder = { v -> UserViewHolder(v) },
            layoutResource = R.layout.item_user_details,
            onBindViewHolder = { vh, _, p -> vh.bind(p) }
        )
        register(
            viewHolder = { v -> PostViewHolder(v) },
            layoutResource = R.layout.item_user_post,
            onBindViewHolder = { vh, _, p -> vh.bind(p) }
        )
    }

    private inner class UserViewHolder(
        view: View
    ) : RecyclerViewHolder<UserDetailItem.UserDetails>(view) {

        private val binding = ItemUserDetailsBinding.bind(view)

        fun bind(item: UserDetailItem.UserDetails) = with(binding) {
            ivProfPic.load(item.user.imageUrl) {
                transformations(CircleCropTransformation())
            }
            tvUsername.text = item.user.name
            tvPostsCount.text = item.postCount.toString()
            tvFollowersCount.text = item.followersCount.toString()
            tvFollowingCount.text = item.followingCount.toString()
        }

    }

    private class PostViewHolder(
        view: View
    ) : RecyclerViewHolder<UserDetailItem.Post>(view) {

        private val binding = ItemUserPostBinding.bind(view)

        fun bind(item: UserDetailItem.Post) = with(binding) {
            tvTitle.text = item.post.title
            tvDescription.text = item.post.body
        }

    }

}