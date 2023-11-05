package application.service.user.endpoint.users

import application.core.model.ResultMessage
import application.core.model.ResultType
import application.core.text.Locale


fun userGetSuccessMessage(locale: Locale) = ResultMessage(
    ResultType.SUCCESS, locale.language.getAllUserSuccessTitle, locale.language.getAllUserSuccessMessage
)

fun userNotFoundMessage(locale: Locale = Locale.TURKISH) = ResultMessage(
    ResultType.INFO, locale.language.checkUserNotFoundTitle, locale.language.checkUserNotFoundMessage
)

fun requireFieldIsEmptyMessage(locale: Locale) = ResultMessage(
    ResultType.FAILURE, "eksik bilgi", "zorunlu alanları doldurunuz"
)