package application.service.user.endpoint.login

import application.core.model.ResultMessage
import application.core.model.ResultType
import application.core.text.Locale

fun wrongPasswordMessage(locale: Locale) = ResultMessage(
    ResultType.INFO, locale.language.checkUserWrongPasswordTitle, locale.language.checkUserWrongPasswordMessage
)

fun loginSuccessMessage(locale: Locale) = ResultMessage(
    ResultType.SUCCESS, locale.language.checkUserSuccessTitle, locale.language.checkUserSuccessMessage
)

fun userNotFoundMessage(locale: Locale) = ResultMessage(
    ResultType.INFO, locale.language.checkUserNotFoundTitle, locale.language.checkUserNotFoundMessage
)

fun requireFieldIsEmptyMessage(locale: Locale) = ResultMessage(
    ResultType.FAILURE, "eksik bilgi", "zorunlu alanları doldurunuz"
)