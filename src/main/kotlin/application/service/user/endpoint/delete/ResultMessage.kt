package application.service.user.endpoint.delete

import application.core.model.ResultMessage
import application.core.model.ResultType
import application.core.text.Locale



fun requireFieldIsEmptyMessage(locale: Locale) = ResultMessage(
    ResultType.FAILURE, "eksik bilgi", "zorunlu alanları doldurunuz"
)
fun notAllowedMessage(locale: Locale = Locale.TURKISH) = ResultMessage(
    ResultType.INFO,
    "Başarısız",
    "Bu hesabı silmek için yetkiniz yok"
)

fun deleteSuccessMessage(locale: Locale = Locale.TURKISH) = ResultMessage(
    ResultType.SUCCESS,
    "Başarılı",
    "Hesap silindi"
)