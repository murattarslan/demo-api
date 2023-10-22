package restaurant.service.user.endpoint

import ignoreNull
import restaurant.core.extentions.createId
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

fun register(
    user: User,
    locale: Locale = Locale.TURKISH
): Response<User> {
    val check = checkUserName(user.username)
    if (check.resultMessage.type != ResultType.SUCCESS)
        return Response(null,check.resultMessage)
    val response = updateQuery(
        "insert into users values (" +
                " '${createId()}'," +
                " '${user.name}'," +
                " '${user.lastname}'," +
                " '${user.username}'," +
                " '${user.password}'," +
                " null," +
                " null," +
                " false," +
                " '${Date().format()}'," +
                " '${Date().format()}'," +
                " null," +
                " null" +
                ")",
        locale,
        registerSuccessMessage(locale)
    )
    return if (response.resultMessage.type == ResultType.ERROR)
        Response(null, response.resultMessage)
    else if (response.result.ignoreNull().not())
        Response(null, registerFailMessage(locale))
    else
        login(user.username, user.password, locale).apply { resultMessage = registerSuccessMessage(locale) }
}

fun registerFailMessage(locale: Locale = Locale.TURKISH) = ResultMessage(
    ResultType.FAILURE,
    "Başarısız",
    "kullanıcı oluşturulamadı"
)

fun registerSuccessMessage(locale: Locale = Locale.TURKISH) = ResultMessage(
    ResultType.SUCCESS,
    "Başarılı",
    "Kullanıcı başarıyla oluşturuldu"
)