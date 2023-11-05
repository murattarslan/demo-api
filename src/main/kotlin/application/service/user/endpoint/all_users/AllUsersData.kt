package application.service.user.endpoint.all_users

import application.core.extentions.lang
import application.core.model.Response
import ignoreNull
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.util.pipeline.*

suspend fun PipelineContext<Unit, ApplicationCall>.deleteUserItemForAdmin(): Pair<Boolean, String>? {
    val principal = call.principal<JWTPrincipal>()
    val id = principal?.payload?.getClaim("id")?.asString()
    val userId = call.parameters["id"]
    val body = call.receive<HashMap<String, Boolean>>()
    val isSafe = body["isSafe"]
    if (id.isNullOrEmpty() || userId.isNullOrEmpty()) {
        call.respond(HttpStatusCode.BadRequest, Response(null, requireFieldIsEmptyMessage(lang())))
        return null
    }
    return Pair(isSafe.ignoreNull(true), userId)
}