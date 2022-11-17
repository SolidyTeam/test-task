package am.solidy.data.db.mapper

import am.solidy.core.entity.UserWithPostsEntity
import am.solidy.core.utils.Mapper
import am.solidy.data.db.entity.UserWithPostsDbEntity

class MapperUserWithPostsDbEntityToDomain : Mapper<UserWithPostsDbEntity, UserWithPostsEntity> {

    private val userMapper = MapperUserDbEntityToDomain()
    private val postMapper = MapperPostDbEntityToDomain()

    override fun map(from: UserWithPostsDbEntity): UserWithPostsEntity = from.run {
        UserWithPostsEntity(
            user = userMapper.map(user),
            posts = postMapper.map(posts)
        )
    }
}