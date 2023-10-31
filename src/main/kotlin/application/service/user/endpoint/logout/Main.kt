package application.service.user.endpoint.logout

import ignoreNull
import application.core.extentions.format
import application.core.model.Response
import application.core.model.ResultMessage
import application.core.model.ResultType
import application.core.plugins.updateQuery
import application.core.text.Locale
import application.service.user.endpoint.check.successMessage
import application.service.user.endpoint.check.userNotFoundMessage
import java.util.*


fun logout(
    userId: String, locale: Locale
): Response<Boolean> {
    val response = updateQuery(
        "update users set "
                + "login_at = null, "
                + "modified_at = '${Date().format()}', "
                + "token = null "
                + "where id = '${userId}'",
        locale,
        logoutSuccessMessage(locale)
    )
    return if (response.resultMessage.type == ResultType.ERROR) Response(false, response.resultMessage)
    else if (response.result.ignoreNull().not()) Response(false, userNotFoundMessage(locale))
    else Response(true, logoutSuccessMessage(locale))
}