package application.service.user.endpoint.check_username

import application.core.model.Response
import application.core.model.ResultType
import application.core.plugins.getQuery
import application.core.text.Locale
import application.service.user.model.toUser

fun checkUserName(username: String, locale: Locale): Response<Boolean> {
    val response = getQuery(checkUsernameQuery(username), locale, checkUsernameFailMessage(locale)) { it.toUser() }
    return if (response.resultMessage.type == ResultType.ERROR) Response(false, response.resultMessage)
    else if (response.result.isNullOrEmpty()) Response(true, checkUsernameSuccessMessage(locale))
    else Response(false, checkUsernameFailMessage(locale))
}