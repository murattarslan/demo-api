package application.service.user.endpoint.get

import application.core.model.Response
import application.core.model.ResultType
import application.core.plugins.getQuery
import application.core.text.Locale
import application.service.user.endpoint.check.successMessage
import application.service.user.endpoint.check.userNotFoundMessage
import application.service.user.model.User
import application.service.user.tools.toUser
import ignoreNull

fun getAllUsersWithDeleted(locale: Locale): Response<ArrayList<User>> {
    return getQuery(
        "select * from users",
        locale,
        createResultMessage(locale)
    ) { it.toUser() }
}
fun getAllUsers(locale: Locale): Response<ArrayList<User>> {
    return getQuery(
        "select * from users where is_delete = false",
        locale,
        createResultMessage(locale)
    ) { it.toUser() }
}
fun getUser(
    userId: String,
    locale: Locale = Locale.TURKISH
): Response<User> {
    val response = getQuery(
        "select * from users where id = '$userId'",
        locale,
        successMessage(locale)
    ){ it.toUser() }
    return if (response.resultMessage.type == ResultType.ERROR)
        Response(null, response.resultMessage)
    else if (response.result.isNullOrEmpty())
        Response(null, userNotFoundMessage(locale))
    else {
        val user = response.result.ignoreNull().first()
        Response(user, successMessage(locale))
    }
}