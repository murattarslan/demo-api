package application.service.user.endpoint.logout

import application.core.model.Response
import application.core.model.ResultType
import application.core.plugins.updateQuery
import application.core.text.Locale
import ignoreNull


fun logout(userId: String, locale: Locale): Response<Boolean> {
    val response = updateQuery(logoutQuery(userId), locale, logoutSuccessMessage(locale))
    return if (response.resultMessage.type == ResultType.ERROR) Response(false, response.resultMessage)
    else if (response.result.ignoreNull().not()) Response(false, userNotFoundMessage(locale))
    else Response(true, logoutSuccessMessage(locale))
}