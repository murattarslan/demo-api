package application.service.user.model

import kotlinx.serialization.Serializable

@Serializable
data class User(
    var id: String,
    var name: String? = null,
    var lastname: String? = null,
    var username: String? = null,
    var password: String? = null,
    var phone: String? = null,
    var mail: String? = null,
    var isDelete: Boolean = false,
    var createdAt: String? = null,
    var modifiedAt: String? = null,
    var loginAt: String? = null,
    var token: String? = null,
    var roleName: String? = null,
    var role: Int? = null
)
