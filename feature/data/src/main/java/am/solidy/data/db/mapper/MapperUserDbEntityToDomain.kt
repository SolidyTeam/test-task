package am.solidy.data.db.mapper

import am.solidy.core.entity.UserEntity
import am.solidy.core.utils.Mapper
import am.solidy.data.db.entity.UserDbEntity

class MapperUserDbEntityToDomain() : Mapper<UserDbEntity, UserEntity> {

    override fun map(from: UserDbEntity): UserEntity = from.run {
        UserEntity(
            id = id,
            albumId = albumId,
            name = name,
            imageUrl = imageUrl,
            thumbnailUrl = thumbnailUrl,
        )
    }
}