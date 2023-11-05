package application.service.user.endpoint.all_users

import application.core.model.Response
import application.core.model.ResultType
import application.core.plugins.getQuery
import application.core.plugins.updateQuery
import application.core.text.Locale
import application.service.user.model.User
import application.service.user.model.toUser
import ignoreNull

fun getAllUsersForAdmin(locale: Locale): Response<ArrayList<User>> {
    return getQuery(getAllUserQueryForAdmin(), locale, userGetSuccessMessage(locale)) { it.toUser() }
}

fun deleteUser(userId: String, isSafe: Boolean, locale: Locale): Response<Boolean> {
    val response = updateQuery(deleteUserQueryForAdmin(userId, isSafe), locale, deleteSuccessMessage(locale))
    return if (response.resultMessage.type == ResultType.ERROR) Response(false, response.resultMessage)
    else if (response.result.ignoreNull().not()) Response(false, userNotFoundMessage(locale))
    else Response(true, deleteSuccessMessage(locale))
}