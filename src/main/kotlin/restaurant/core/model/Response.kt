package restaurant.core.model

import kotlinx.serialization.Serializable

@Serializable
data class Response<T>(
    var result: T?,
    var resultMessage: ResultMessage
)
