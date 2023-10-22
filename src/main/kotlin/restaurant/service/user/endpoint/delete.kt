package restaurant.service.user.endpoint

import ignoreNull
import restaurant.core.model.Response
import restaurant.core.model.ResultMessage
import restaurant.core.model.ResultType
import restaurant.core.plugins.updateQuery
import restaurant.core.text.Locale

fun deleteUserGateway(
    tokenId: String,
    userId: String,
    locale: Locale = Locale.TURKISH,
    isSafe: Boolean = true
): Response<Boolean>{
    return if (tokenId.length < 30){ // admin ise
        if (isSafe)
            safeDeleteUser(userId,locale)
        else
            deleteUser(userId,locale)
    }
    else{
        Response(false, notAllowedMessage(locale))
    }
}
fun deleteUser(
    userId: String,
    locale: Locale = Locale.TURKISH
): Response<Boolean> {
    val response = updateQuery(
        "delete from users where id = '${userId}'",
        locale,
        deleteSuccessMessage(locale)
    )
    return if (response.resultMessage.type == ResultType.ERROR)
        Response(false, response.resultMessage)
    else if (response.result.ignoreNull().not())
        Response(false, userNotFoundMessage(locale))
    else
        Response(true, deleteSuccessMessage(locale))
}

fun safeDeleteUser(
    userId: String,
    locale: Locale = Locale.TURKISH
): Response<Boolean> {
    val response = updateQuery(
        "update users set is_delete = true, token = null, login_at = null where id = '${userId}'",
        locale,
        deleteSuccessMessage(locale)
    )
    return if (response.resultMessage.type == ResultType.ERROR)
        Response(false, response.resultMessage)
    else if (response.result.ignoreNull().not())
        Response(false, userNotFoundMessage(locale))
    else
        Response(true, deleteSuccessMessage(locale))
}

fun notAllowedMessage(locale: Locale = Locale.TURKISH) = ResultMessage(
    ResultType.INFO,
    "Başarısız",
    "Bu hesabı silmek için yetkiniz yok"
)

fun deleteSuccessMessage(locale: Locale = Locale.TURKISH) = ResultMessage(
    ResultType.SUCCESS,
    "Başarılı",
    "Hesap silindi"
)