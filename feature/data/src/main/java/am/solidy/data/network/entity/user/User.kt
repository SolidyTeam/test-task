package am.solidy.data.network.entity.user


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class User(
    @SerialName("userId") val userId: Int,
    @SerialName("albumId") val albumId: Int,
    @SerialName("name") val name: String?,
    @SerialName("thumbnailUrl") val thumbnailUrl: String?,
    @SerialName("url") val url: String?
)