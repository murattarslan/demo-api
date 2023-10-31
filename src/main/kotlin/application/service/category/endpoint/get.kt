package application.service.category.endpoint

import application.core.model.Response
import application.core.model.ResultMessage
import application.core.model.ResultType
import application.core.plugins.getQuery
import application.core.text.Locale
import application.service.category.model.Category
import application.service.category.tools.toCategory

fun getAllCategory(locale: Locale = Locale.TURKISH): Response<ArrayList<Category>> {
    return getQuery(
        "select * from Category",
        locale,
        createResultMessage(locale)
    ) { it.toCategory() }
}

fun createResultMessage(locale: Locale = Locale.TURKISH) = ResultMessage(
    ResultType.SUCCESS,
    locale.language.getAllCategorySuccessTitle,
    locale.language.getAllCategorySuccessMessage
)