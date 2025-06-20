package application.core.extentions

import ignoreNull
import io.ktor.server.application.*
import io.ktor.util.pipeline.*
import application.core.text.HeaderParams
import application.core.text.Locale

fun PipelineContext<Unit, ApplicationCall>.version() = call.request.headers[HeaderParams.api_version].ignoreNull("1")
fun PipelineContext<Unit, ApplicationCall>.lang(): Locale {
    val lang = call.request.headers[HeaderParams.language]
    return Locale.entries.firstOrNull { it.lang.equals(lang, true) } ?: Locale.TURKISH
}