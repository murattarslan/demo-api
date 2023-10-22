package restaurant.service.user.model

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: String,
    var name: String,
    var lastname: String,
    var username: String,
    var password: String,
    var phone: String? = null,
    var mail: String? = null,
    var isDelete: Boolean = false,
    var createdAt: String? = null,
    var modifiedAt: String? = null,
    var loginAt: String? = null,
    var token: String? = null
)
