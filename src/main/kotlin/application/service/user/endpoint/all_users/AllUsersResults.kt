package application.service.user.endpoint.all_users

import application.core.model.ResultMessage
import application.core.model.ResultType
import application.core.text.Locale

fun deleteSuccessMessage(locale: Locale) = ResultMessage(
    ResultType.SUCCESS, "Başarılı", "Hesap silindi"
)

fun userGetSuccessMessage(locale: Locale) = ResultMessage(
    ResultType.SUCCESS, locale.language.getAllUserSuccessTitle, locale.language.getAllUserSuccessMessage
)

fun userNotFoundMessage(locale: Locale) = ResultMessage(
    ResultType.INFO, locale.language.checkUserNotFoundTitle, locale.language.checkUserNotFoundMessage
)

fun requireFieldIsEmptyMessage(locale: Locale) = ResultMessage(
    ResultType.FAILURE, "eksik bilgi", "zorunlu alanları doldurunuz"
)