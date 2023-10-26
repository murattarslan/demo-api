package restaurant.service.media.model

import kotlinx.serialization.Serializable

@Serializable
data class Media(
    val id: String,
    var url: String,
    var thumb: String,
    var type: String,
    var mimeType: String,
    var height: Int,
    var width: Int
)
