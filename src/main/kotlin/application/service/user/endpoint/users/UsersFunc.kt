package application.service.user.endpoint.users

import application.core.model.Response
import application.core.model.ResultType
import application.core.plugins.getQuery
import application.core.text.Locale
import application.service.user.model.User
import application.service.user.model.toUser
import ignoreNull

fun getAllUsers(locale: Locale): Response<ArrayList<User>> {
    return getQuery(
        getAllUserQuery(), locale, userGetSuccessMessage(locale)
    ) { it.toUser() }
}

fun getUser(
    userId: String, locale: Locale
): Response<User> {
    val response = getQuery(
        getUserQuery(userId), locale, userGetSuccessMessage(locale)
    ) { it.toUser() }
    return if (response.resultMessage.type == ResultType.ERROR) Response(null, response.resultMessage)
    else if (response.result.isNullOrEmpty()) Response(
        null,
        application.service.user.endpoint.all_users.userNotFoundMessage(locale)
    )
    else {
        val user = response.result.ignoreNull().first()
        Response(user, userGetSuccessMessage(locale))
    }
}