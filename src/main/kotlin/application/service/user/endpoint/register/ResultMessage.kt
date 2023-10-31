package application.service.user.endpoint.register

import application.core.model.ResultMessage
import application.core.model.ResultType
import application.core.text.Locale


fun requireFieldIsEmptyMessage(locale: Locale) = ResultMessage(
    ResultType.FAILURE, "eksik bilgi", "zorunlu alanları doldurunuz"
)

fun registerFailMessage(locale: Locale) = ResultMessage(
    ResultType.FAILURE,
    "Başarısız",
    "kullanıcı oluşturulamadı"
)

fun registerSuccessMessage(locale: Locale) = ResultMessage(
    ResultType.SUCCESS,
    "Başarılı",
    "Kullanıcı başarıyla oluşturuldu"
)