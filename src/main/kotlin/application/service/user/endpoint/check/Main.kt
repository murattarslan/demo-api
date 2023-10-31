package application.service.user.endpoint.check

import application.core.model.Response
import application.core.model.ResultType
import application.core.plugins.getQuery
import application.core.text.Locale
import application.service.user.model.User
import application.service.user.tools.toUser
import ignoreNull

fun checkUserName(
    username: String,
    locale: Locale = Locale.TURKISH
): Response<Boolean> {
    val response = getQuery(
        "select * from users where username = '$username'",
        locale,
        checkUsernameSuccessMessage(locale)
    ){ it.toUser() }
    return if (response.resultMessage.type == ResultType.ERROR)
        Response(false, response.resultMessage)
    else if (response.result.isNullOrEmpty())
        Response(true, checkUsernameSuccessMessage(locale))
    else {
        Response(false, checkUsernameFailMessage(locale))
    }
}