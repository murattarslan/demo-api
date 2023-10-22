package restaurant.service.user.endpoint

import restaurant.core.model.Response
import restaurant.core.model.ResultMessage
import restaurant.core.model.ResultType
import restaurant.core.plugins.getQuery
import restaurant.core.text.Locale
import restaurant.service.user.model.User
import restaurant.service.user.tools.toUser

fun getAllUsers(isSafe: Boolean, locale: Locale = Locale.TURKISH): Response<ArrayList<User>> {
    return getQuery(
        if (isSafe) "select * from users" else "select * from users where is_delete = false",
        locale,
        createResultMessage(locale)
    ) { it.toUser() }
}

fun createResultMessage(locale: Locale = Locale.TURKISH) = ResultMessage(
    ResultType.SUCCESS,
    locale.language.getAllUserSuccessTitle,
    locale.language.getAllUserSuccessMessage
)