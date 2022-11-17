package am.solidy.data.dp.mapper

import am.solidy.core.entity.PostEntity
import am.solidy.core.utils.Mapper
import am.solidy.data.dp.entity.PostDbEntity

class MapperPostDbEntityToDomain : Mapper<PostDbEntity, PostEntity> {

    override fun map(from: PostDbEntity): PostEntity = from.run {
        PostEntity(
            id = id,
            title = title,
            body = body
        )
    }
}