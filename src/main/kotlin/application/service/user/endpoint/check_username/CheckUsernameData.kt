package application.service.user.endpoint.check_username

import application.core.extentions.lang
import application.core.model.Response
import ignoreNull
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.util.pipeline.*

suspend fun PipelineContext<Unit, ApplicationCall>.checkUserNameItem(): String? {
    val body = call.receive<HashMap<String, String>>()
    val username = body["username"]
    if (username.isNullOrEmpty()) {
        call.respond(HttpStatusCode.BadRequest, Response(null, requireFieldIsEmptyMessage(lang())))
        return null
    }
    return username.ignoreNull()
}