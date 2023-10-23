package restaurant.service.user.endpoint

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.typesafe.config.ConfigFactory
import ignoreNull
import io.ktor.server.config.*
import restaurant.core.extentions.format
import restaurant.core.model.Response
import restaurant.core.model.ResultMessage
import restaurant.core.model.ResultType
import restaurant.core.plugins.getQuery
import restaurant.core.plugins.updateQuery
import restaurant.core.text.Locale
import restaurant.service.user.model.User
import restaurant.service.user.tools.toUser
import java.util.*


fun logout(
    userId: String?,
    locale: Locale = Locale.TURKISH
): Response<Boolean> {
    if (userId == null)
        return Response(false, userNotFoundMessage(locale))
    val response = updateQuery(
        "update users set " +
                "login_at = null, " +
                "modified_at = '${Date().format()}', " +
                "token = null " +
                "where id = '${userId}'",
        locale,
        successMessage(locale)
    )
    return if (response.resultMessage.type == ResultType.ERROR)
        Response(false, response.resultMessage)
    else if (response.result.ignoreNull().not())
        Response(false, userNotFoundMessage(locale))
    else
        Response(true, logoutSuccessMessage(locale))
}

fun logoutSuccessMessage(locale: Locale = Locale.TURKISH) = ResultMessage(
    ResultType.SUCCESS,
    "Başarılı",
    "Oturumunuz sonlandırılmıştır"
)