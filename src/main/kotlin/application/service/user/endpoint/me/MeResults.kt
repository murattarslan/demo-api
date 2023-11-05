package application.service.user.endpoint.me

import application.core.model.ResultMessage
import application.core.model.ResultType
import application.core.text.Locale


fun meSuccessMessage(locale: Locale) = ResultMessage(
    ResultType.SUCCESS, locale.language.getAllUserSuccessTitle, locale.language.getAllUserSuccessMessage
)

fun deleteSuccessMessage(locale: Locale) = ResultMessage(
    ResultType.SUCCESS, "Başarılı", "Hesap silindi"
)

fun userNotFoundMessage(locale: Locale) = ResultMessage(
    ResultType.INFO, locale.language.checkUserNotFoundTitle, locale.language.checkUserNotFoundMessage
)

fun requireFieldIsEmptyMessage(locale: Locale) = ResultMessage(
    ResultType.FAILURE, "eksik bilgi", "zorunlu alanları doldurunuz"
)

fun usernameUpdateSuccessMessage(locale: Locale) = ResultMessage(
    ResultType.SUCCESS, "Başarılı", "Kullanıcı adı güncellendi"
)

fun userPasswordUpdateSuccessMessage(locale: Locale) = ResultMessage(
    ResultType.SUCCESS, "Başarılı", "Kullanıcı şifresi güncellendi"
)

fun userUpdateFailMessage(locale: Locale) = ResultMessage(
    ResultType.SUCCESS, "Başarılı", "Kullanıcı güncellendi"
)

fun userUpdateSuccessMessage(locale: Locale) = ResultMessage(
    ResultType.SUCCESS, "Başarılı", "Kullanıcı güncellendi"
)