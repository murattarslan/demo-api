package application.service.user.endpoint.get

import application.core.model.ResultMessage
import application.core.model.ResultType
import application.core.text.Locale


fun createResultMessage(locale: Locale) = ResultMessage(
    ResultType.SUCCESS,
    locale.language.getAllUserSuccessTitle,
    locale.language.getAllUserSuccessMessage
)