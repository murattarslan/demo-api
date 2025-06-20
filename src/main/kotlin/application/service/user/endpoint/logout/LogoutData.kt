package application.service.user.endpoint.logout

import application.core.extentions.lang
import application.core.model.Response
import ignoreNull
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.response.*
import io.ktor.util.pipeline.*

suspend fun PipelineContext<Unit, ApplicationCall>.checkUserByToken(): String? {
    val principal = call.principal<JWTPrincipal>()
    val id = principal?.payload?.getClaim("id")?.asString()
    if (id.isNullOrEmpty()) {
        call.respond(HttpStatusCode.BadRequest, Response(null, requireFieldIsEmptyMessage(lang())))
        return null
    }
    return id.ignoreNull()
}