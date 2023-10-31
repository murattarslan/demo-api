package application.core.model

import kotlinx.serialization.Serializable

@Serializable
data class ResultMessage(
    val type: ResultType = ResultType.SUCCESS,
    val title: String = "başarılı",
    val message: String = "başarılı"
)
