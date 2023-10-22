package restaurant.service.user.endpoint

import ignoreNull
import restaurant.core.extentions.format
import restaurant.core.model.Response
import restaurant.core.model.ResultMessage
import restaurant.core.model.ResultType
import restaurant.core.plugins.getQuery
import restaurant.core.plugins.updateQuery
import restaurant.core.text.Locale
import restaurant.service.user.model.User
import restaurant.service.user.tools.toUser
import java.util.*

fun updateUserForLogin(
    user: User,
    locale: Locale = Locale.TURKISH
): Response<User> {
    val response = updateQuery(
        "update users set login_at = '${user.loginAt}', modified_at = '${Date().format()}', token = '${user.token}' where id = '${user.id}'",
        locale,
        successMessage(locale)
    )
    return if (response.resultMessage.type == ResultType.ERROR)
        Response(null, response.resultMessage)
    else if (response.result.ignoreNull().not())
        Response(null, userNotFoundMessage(locale))
    else
        Response(user, successMessage(locale))
}

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
        return Response(null,check.resultMessage)
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
    val response = updateQuery(
        "update users set " +
                "name = '${user.name}', " +
                "lastname = '${user.lastname}', " +
                "phone = '${user.phone}', " +
                "mail = '${user.mail}', " +
                "modified_at = '${Date().format()}' " +
                "where id = '${user.id}'",
        locale,
        userUpdateSuccessMessage(locale)
    )
    return if (response.resultMessage.type == ResultType.ERROR)
        Response(null, response.resultMessage)
    else if (response.result.ignoreNull().not())
        Response(null, userNotFoundMessage(locale))
    else
        Response(user, userUpdateSuccessMessage(locale))
}

fun usernameUpdateSuccessMessage(locale: Locale = Locale.TURKISH) = ResultMessage(
    ResultType.SUCCESS,
    "Başarılı",
    "Kullanıcı adı güncellendi"
)

fun userPasswordUpdateSuccessMessage(locale: Locale = Locale.TURKISH) = ResultMessage(
    ResultType.SUCCESS,
    "Başarılı",
    "Kullanıcı şifresi güncellendi"
)

fun userUpdateSuccessMessage(locale: Locale = Locale.TURKISH) = ResultMessage(
    ResultType.SUCCESS,
    "Başarılı",
    "Kullanıcı güncellendi"
)