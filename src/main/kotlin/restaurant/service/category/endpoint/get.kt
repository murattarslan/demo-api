package restaurant.service.category.endpoint

import restaurant.core.model.Response
import restaurant.core.model.ResultMessage
import restaurant.core.model.ResultType
import restaurant.core.plugins.getQuery
import restaurant.core.text.Locale
import restaurant.service.category.model.Category
import restaurant.service.category.tools.toCategory

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