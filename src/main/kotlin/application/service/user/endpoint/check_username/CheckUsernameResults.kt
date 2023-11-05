package application.service.user.endpoint.check_username

import application.core.model.ResultMessage
import application.core.model.ResultType
import application.core.text.Locale


fun checkUsernameFailMessage(locale: Locale) = ResultMessage(
    ResultType.INFO, "eşleşme bulundu", "bu kullanıcı adı kullanılıyor"
)

fun checkUsernameSuccessMessage(locale: Locale) = ResultMessage(
    ResultType.SUCCESS, "Başarılı", "Bu kullanıcı adını kullanabilirsiniz"
)

fun requireFieldIsEmptyMessage(locale: Locale) = ResultMessage(
    ResultType.FAILURE, "eksik bilgi", "zorunlu alanları doldurunuz"
)