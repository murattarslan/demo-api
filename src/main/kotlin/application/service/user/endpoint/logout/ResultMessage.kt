package application.service.user.endpoint.logout

import application.core.model.ResultMessage
import application.core.model.ResultType
import application.core.text.Locale

fun logoutSuccessMessage(locale: Locale) = ResultMessage(
    ResultType.SUCCESS, "Başarılı", "Oturumunuz sonlandırılmıştır"
)