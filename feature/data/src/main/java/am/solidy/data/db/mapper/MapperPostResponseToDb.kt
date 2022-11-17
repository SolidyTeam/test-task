package am.solidy.data.db.mapper

import am.solidy.core.utils.Mapper
import am.solidy.data.db.entity.PostDbEntity
import am.solidy.data.network.entity.post.Post

class MapperPostResponseToDb : Mapper<Post, PostDbEntity> {

    override fun map(from: Post): PostDbEntity = from.run {
        PostDbEntity(
            id = id,
            userId = userId,
            title = title.orEmpty(),
            body = body.orEmpty()
        )
    }
}