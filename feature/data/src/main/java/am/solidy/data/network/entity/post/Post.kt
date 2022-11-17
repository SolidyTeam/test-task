package am.solidy.data.network.entity.post


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Post(
    @SerialName("id") val id: Int,
    @SerialName("userId") val userId: Int,
    @SerialName("body") val body: String?,
    @SerialName("title") val title: String?
)