package am.solidy.data.dp.mapper

import am.solidy.core.entity.UserEntity
import am.solidy.core.utils.Mapper
import am.solidy.data.dp.entity.UserDbEntity

class MapperUserDbEntityToDomain : Mapper<UserDbEntity, UserEntity> {

    override fun map(from: UserDbEntity): UserEntity = from.run {
        UserEntity(
            id = id,
            albumId = albumId,
            name = name,
            imageUrl = imageUrl,
            thumbnailUrl = thumbnailUrl
        )
    }
}