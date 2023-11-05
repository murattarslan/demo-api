package application.service.user.endpoint.register

import application.core.extentions.createId
import application.core.extentions.format
import application.core.model.Response
import application.core.model.ResultType
import application.core.plugins.updateQuery
import application.core.text.Locale
import application.service.user.model.User
import application.core.extentions.createToken
import ignoreNull
import java.util.*

fun register(user: User, locale: Locale): Response<User> {
    user.id = createId()
    user.loginAt = Date().format()
    user.token = createToken(user.id, user.loginAt!!)
    val response = updateQuery(
        insertRegisterQuery(user),
        locale,
        registerFailMessage(locale)
    )
    return if (response.resultMessage.type == ResultType.ERROR)
        Response(null, response.resultMessage)
    else if (response.result.ignoreNull().not())
        Response(null, registerFailMessage(locale))
    else
        Response(user, registerSuccessMessage(locale))
}