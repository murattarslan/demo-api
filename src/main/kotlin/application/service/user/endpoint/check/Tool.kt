package application.service.user.endpoint.check

import application.core.extentions.lang
import application.core.model.Response
import application.core.model.ResultMessage
import application.core.model.ResultType
import application.core.text.Locale
import ignoreNull
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.util.pipeline.*


suspend fun PipelineContext<Unit, ApplicationCall>.checkUserIdItemByParams(): String?{
    val id = call.parameters["id"]
    if (id.isNullOrEmpty()) {
        call.respond(HttpStatusCode.BadRequest, Response(null, requireFieldIsEmptyMessage(lang())))
        return null
    }
    return id.ignoreNull()
}

suspend fun PipelineContext<Unit, ApplicationCall>.checkUserIdItemByToken(): String?{
    val principal = call.principal<JWTPrincipal>()
    val id = principal?.payload?.getClaim("id")?.asString()
    if (id.isNullOrEmpty()) {
        call.respond(HttpStatusCode.BadRequest, Response(null, requireFieldIsEmptyMessage(lang())))
        return null
    }
    return id.ignoreNull()
}

suspend fun PipelineContext<Unit, ApplicationCall>.checkUserNameItem(): String?{
    val body = call.receive<HashMap<String, String>>()
    val username = body["username"]
    if (username.isNullOrEmpty()) {
        call.respond(HttpStatusCode.BadRequest, Response(null, requireFieldIsEmptyMessage(lang())))
        return null
    }
    return username.ignoreNull()
}