package application.service.user.endpoint.update

import ignoreNull
import application.core.extentions.format
import application.core.model.Response
import application.core.model.ResultType
import application.core.plugins.updateQuery
import application.core.text.Locale
import application.service.user.endpoint.check.checkUserName
import application.service.user.endpoint.get.getUser
import application.service.user.model.User
import java.util.*

fun updateUserPassword(
    password: String,
    id: String,
    locale: Locale = Locale.TURKISH
): Response<Boolean> {
    val response = updateQuery(
        "update users set " +
                "password = '${password}', " +
                "modified_at = '${Date().format()}' " +
                "where id = '${id}'",
        locale,
        userPasswordUpdateSuccessMessage(locale)
    )
    return if (response.resultMessage.type == ResultType.ERROR)
        Response(false, response.resultMessage)
    else if (response.result.ignoreNull().not())
        Response(false, userNotFoundMessage(locale))
    else
        Response(true, userPasswordUpdateSuccessMessage(locale))
}

fun updateUsername(
    username: String,
    id: String,
    locale: Locale = Locale.TURKISH
): Response<Boolean> {
    val check = checkUserName(username)
    if (check.resultMessage.type != ResultType.SUCCESS)
        return Response(null, check.resultMessage)
    val response = updateQuery(
        "update users set " +
                "username = '${username}', " +
                "modified_at = '${Date().format()}' " +
                "where id = '${id}'",
        locale,
        usernameUpdateSuccessMessage(locale)
    )
    return if (response.resultMessage.type == ResultType.ERROR)
        Response(false, response.resultMessage)
    else if (response.result.ignoreNull().not())
        Response(false, userNotFoundMessage(locale))
    else
        Response(true, usernameUpdateSuccessMessage(locale))
}

fun updateUser(
    user: User,
    locale: Locale = Locale.TURKISH
): Response<User> {
    val (mUser, _) = getUser(user.id, locale)
    val response = if (mUser != null) {
        updateQuery(
            "update users set " +
                    "name = '${user.name ?: mUser.name}', " +
                    "lastname = '${user.lastname ?: mUser.lastname}', " +
                    "phone = '${user.phone ?: mUser.phone}', " +
                    "mail = '${user.mail ?: mUser.mail}', " +
                    "modified_at = '${Date().format()}' " +
                    "where id = '${user.id}'",
            locale,
            userUpdateSuccessMessage(locale)
        )
    } else return Response(null, userNotFoundMessage())
    return if (response.resultMessage.type == ResultType.ERROR)
        Response(null, response.resultMessage)
    else if (response.result.ignoreNull().not())
        Response(null, userNotFoundMessage(locale))
    else
        Response(user, userUpdateSuccessMessage(locale))
}