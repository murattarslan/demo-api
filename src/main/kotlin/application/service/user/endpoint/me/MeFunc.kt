package application.service.user.endpoint.me

import application.core.extentions.format
import application.core.model.Response
import application.core.model.ResultType
import application.core.plugins.getQuery
import application.core.plugins.updateQuery
import application.core.text.Locale
import application.service.user.model.User
import application.service.user.model.toUser
import ignoreNull
import java.util.*


fun getMe(userId: String, locale: Locale): Response<User> {
    val response = getQuery(
        getMeQuery(userId), locale, userNotFoundMessage(locale)
    ) { it.toUser() }
    return if (response.resultMessage.type == ResultType.ERROR) Response(null, response.resultMessage)
    else if (response.result.isNullOrEmpty()) Response(null, userNotFoundMessage(locale))
    else {
        val user = response.result.ignoreNull().first()
        Response(user, meSuccessMessage(locale))
    }
}

fun deleteMe(userId: String, locale: Locale): Response<Boolean> {
    val response = updateQuery(
        deleteMeQuery(userId), locale, deleteSuccessMessage(locale)
    )
    return if (response.resultMessage.type == ResultType.ERROR) Response(false, response.resultMessage)
    else if (response.result.ignoreNull().not()) Response(false, userNotFoundMessage(locale))
    else Response(true, deleteSuccessMessage(locale))
}

fun updateMe(user: User, locale: Locale): Response<User> {
    val response = updateQuery(
        updateMeQuery(user), locale, userUpdateFailMessage(locale)
    )
    val (mUser, _) = getMe(user.id, locale)
    return if (response.resultMessage.type == ResultType.ERROR) Response(null, response.resultMessage)
    else if (response.result.ignoreNull().not()) Response(null, userUpdateFailMessage(locale))
    else Response(mUser, userUpdateSuccessMessage(locale))
}

fun updatePassword(password: String, id: String, locale: Locale): Response<Boolean> {
    val response = updateQuery(
        "update users set " + "password = '${password}', " + "modified_at = '${Date().format()}' " + "where id = '${id}'",
        locale,
        userPasswordUpdateSuccessMessage(locale)
    )
    return if (response.resultMessage.type == ResultType.ERROR) Response(false, response.resultMessage)
    else if (response.result.ignoreNull().not()) Response(false, userNotFoundMessage(locale))
    else Response(true, userPasswordUpdateSuccessMessage(locale))
}

fun updateUsername(username: String, id: String, locale: Locale): Response<Boolean> {
    val response = updateQuery(
        "update \"Users\" set " + "username = '${username}', " + "modified_at = '${Date().format()}' " + "where id = '${id}'",
        locale,
        usernameUpdateSuccessMessage(locale)
    )
    return if (response.resultMessage.type == ResultType.ERROR) Response(false, response.resultMessage)
    else if (response.result.ignoreNull().not()) Response(false, userNotFoundMessage(locale))
    else Response(true, usernameUpdateSuccessMessage(locale))
}
