package am.solidy.data.db.mapper

import am.solidy.core.utils.Mapper
import am.solidy.data.db.entity.UserDbEntity
import am.solidy.data.network.entity.user.User

class MapperUserResponseToDb : Mapper<User, UserDbEntity> {

    override fun map(from: User): UserDbEntity = from.run {
        UserDbEntity(
            id = userId,
            albumId = albumId,
            name = name.orEmpty(),
            imageUrl = url.orEmpty(),
            thumbnailUrl = thumbnailUrl.orEmpty()
        )
    }
}