package application.service.user.endpoint.update

import application.core.model.ResultMessage
import application.core.model.ResultType
import application.core.text.Locale

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

fun userNotFoundMessage(locale: Locale = Locale.TURKISH) = ResultMessage(
    ResultType.INFO,
    locale.language.checkUserNotFoundTitle,
    locale.language.checkUserNotFoundMessage
)

fun requireFieldIsEmptyMessage(locale: Locale) = ResultMessage(
    ResultType.FAILURE, "eksik bilgi", "zorunlu alanları doldurunuz"
)