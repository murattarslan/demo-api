package application.service.user.endpoint.logout

import application.core.model.ResultMessage
import application.core.model.ResultType
import application.core.text.Locale

fun logoutSuccessMessage(locale: Locale) = ResultMessage(
    ResultType.SUCCESS, "Başarılı", "Oturumunuz sonlandırılmıştır"
)

fun userNotFoundMessage(locale: Locale) = ResultMessage(
    ResultType.INFO, locale.language.checkUserNotFoundTitle, locale.language.checkUserNotFoundMessage
)

fun requireFieldIsEmptyMessage(locale: Locale) = ResultMessage(
    ResultType.FAILURE, "eksik bilgi", "zorunlu alanları doldurunuz"
)