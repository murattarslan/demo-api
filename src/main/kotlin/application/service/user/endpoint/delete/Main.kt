package application.service.user.endpoint.delete

import ignoreNull
import application.core.model.Response
import application.core.model.ResultMessage
import application.core.model.ResultType
import application.core.plugins.updateQuery
import application.core.text.Locale
import application.service.user.endpoint.check.userNotFoundMessage

fun deleteUser(
    userId: String,
    locale: Locale = Locale.TURKISH
): Response<Boolean> {
    val response = updateQuery(
        "delete from users where id = '${userId}'",
        locale,
        deleteSuccessMessage(locale)
    )
    return if (response.resultMessage.type == ResultType.ERROR)
        Response(false, response.resultMessage)
    else if (response.result.ignoreNull().not())
        Response(false, userNotFoundMessage(locale))
    else
        Response(true, deleteSuccessMessage(locale))
}

fun safeDeleteUser(
    userId: String,
    locale: Locale = Locale.TURKISH
): Response<Boolean> {
    val response = updateQuery(
        "update users set is_delete = true, token = null, login_at = null where id = '${userId}'",
        locale,
        deleteSuccessMessage(locale)
    )
    return if (response.resultMessage.type == ResultType.ERROR)
        Response(false, response.resultMessage)
    else if (response.result.ignoreNull().not())
        Response(false, userNotFoundMessage(locale))
    else
        Response(true, deleteSuccessMessage(locale))
}