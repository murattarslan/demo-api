package application.service.user.endpoint.check

import application.core.model.ResultMessage
import application.core.model.ResultType
import application.core.text.Locale


fun checkUsernameFailMessage(locale: Locale = Locale.TURKISH) = ResultMessage(
    ResultType.INFO,
    "eşleşme bulundu",
    "bu kullanıcı adı kullanılıyor"
)

fun checkUsernameSuccessMessage(locale: Locale = Locale.TURKISH) = ResultMessage(
    ResultType.SUCCESS,
    "Başarılı",
    "Bu kullanıcı adını kullanabilirsiniz"
)

fun userNotFoundMessage(locale: Locale = Locale.TURKISH) = ResultMessage(
    ResultType.INFO,
    locale.language.checkUserNotFoundTitle,
    locale.language.checkUserNotFoundMessage
)

fun successMessage(locale: Locale = Locale.TURKISH) = ResultMessage(
    ResultType.SUCCESS,
    locale.language.checkUserSuccessTitle,
    locale.language.checkUserSuccessMessage
)

fun requireFieldIsEmptyMessage(locale: Locale) = ResultMessage(
    ResultType.FAILURE, "eksik bilgi", "zorunlu alanları doldurunuz"
)