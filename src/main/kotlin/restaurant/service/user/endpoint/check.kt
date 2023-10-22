package restaurant.service.user.endpoint

import ignoreNull
import restaurant.core.model.Response
import restaurant.core.model.ResultMessage
import restaurant.core.model.ResultType
import restaurant.core.plugins.getQuery
import restaurant.core.text.Locale
import restaurant.service.user.model.User
import restaurant.service.user.tools.toUser

fun checkUser(
    userId: String?,
    locale: Locale = Locale.TURKISH
): Response<User> {
    if (userId == null)
        return Response(null, userNotFoundMessage(locale))
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

fun checkUsernameFailMessage(locale: Locale = Locale.TURKISH) = ResultMessage(
    ResultType.INFO,
    "eşleşme bulundu",
    "bu kullanıcı adı kullanılıyor"
)

fun checkUsernameSuccessMessage(locale: Locale = Locale.TURKISH) = ResultMessage(
    ResultType.SUCCESS,
    "Başarılı",
    "Bu kullanıcı adını kullanabilirsiniz"
)

fun userNotFoundMessage(locale: Locale = Locale.TURKISH) = ResultMessage(
    ResultType.INFO,
    locale.language.checkUserNotFoundTitle,
    locale.language.checkUserNotFoundMessage
)

fun successMessage(locale: Locale = Locale.TURKISH) = ResultMessage(
    ResultType.SUCCESS,
    locale.language.checkUserSuccessTitle,
    locale.language.checkUserSuccessMessage
)