package application.service.user.endpoint.login

import application.core.extentions.format
import application.core.model.Response
import application.core.model.ResultType
import application.core.plugins.getQuery
import application.core.plugins.updateQuery
import application.core.text.Locale
import application.service.user.model.User
import application.core.extentions.createToken
import application.service.user.model.toUser
import ignoreNull
import java.util.*


fun login(username: String, password: String, locale: Locale): Response<User> {
    val response = getQuery(checkLoginQuery(username), locale, userNotFoundMessage(locale)) { it.toUser() }
    return if (response.resultMessage.type == ResultType.ERROR) Response(null, response.resultMessage)
    else if (response.result.isNullOrEmpty()) Response(null, userNotFoundMessage(locale))
    else if (response.result.ignoreNull().none { it.password == password }) Response(null, wrongPasswordMessage(locale))
    else {
        val user = response.result.ignoreNull().first { it.password == password }
        user.loginAt = Date().format()
        user.token = createToken(user.id, user.loginAt!!)

        val booleanResponse = updateQuery(getLoginQuery(user), locale, userNotFoundMessage(locale))
        if (booleanResponse.resultMessage.type == ResultType.ERROR) Response(null, booleanResponse.resultMessage)
        else if (booleanResponse.result.ignoreNull().not()) Response(null, userNotFoundMessage(locale))
        else Response(user, loginSuccessMessage(locale))
    }
}