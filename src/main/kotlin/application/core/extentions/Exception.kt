package application.core.extentions

import ignoreNull
import application.core.model.ResultMessage
import application.core.model.ResultType
import application.core.text.Locale
import java.sql.SQLException

fun Exception.toResultMessage(locale: Locale = Locale.TURKISH): ResultMessage {
    val lang = locale.language
    val (title, message) = when (this) {
        is SQLException -> Pair(lang.sqlErrorTitle, message.ignoreNull()) //lang.sqlErrorMessage)
        is IllegalStateException -> Pair(lang.illegalStateErrorTitle, lang.illegalStateErrorMessage)
        is IllegalArgumentException -> Pair(lang.illegalArgumentErrorTitle, lang.illegalArgumentErrorMessage)
        is ClassCastException -> Pair(lang.classCastErrorTitle, lang.classCastErrorMessage)
        is IndexOutOfBoundsException -> Pair(lang.indexOfBoundErrorTitle, lang.indexOfBoundErrorMessage)
        is NoSuchElementException -> Pair(lang.noSuchElementErrorTitle, lang.noSuchElementErrorMessage)
        is NullPointerException -> Pair(lang.nullPointerErrorTitle, lang.nullPointerErrorMessage)
        else -> Pair(lang.baseErrorTitle, lang.baseErrorMessage)
    }
    println()
    print(localizedMessage)
    println()
    return ResultMessage(ResultType.ERROR, title, message)
}