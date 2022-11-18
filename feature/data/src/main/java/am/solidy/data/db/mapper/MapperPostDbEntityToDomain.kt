package am.solidy.data.db.mapper

import am.solidy.core.entity.PostEntity
import am.solidy.core.utils.Mapper
import am.solidy.data.db.entity.PostDbEntity

class MapperPostDbEntityToDomain : Mapper<PostDbEntity, PostEntity> {

    override fun map(from: PostDbEntity): PostEntity = from.run {
        PostEntity(
            id = id,
            userId = userId,
            title = title,
            body = body
        )
    }
}