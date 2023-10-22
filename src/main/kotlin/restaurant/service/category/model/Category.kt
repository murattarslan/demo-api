package restaurant.service.category.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Category(
    val id: String,
    val name: String,
    @SerialName("created_at")
    val createdAt: String,
    @SerialName("modified_at")
    val modifiedAt: String
)
