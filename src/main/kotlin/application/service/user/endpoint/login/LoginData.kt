package application.service.user.endpoint.login

import application.core.extentions.lang
import application.core.model.Response
import ignoreNull
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.util.pipeline.*

suspend fun PipelineContext<Unit, ApplicationCall>.loginItem(): Pair<String, String>? {
    val body = call.receive<HashMap<String, String>>()
    val username = body["username"]
    val password = body["password"]
    if (username.isNullOrEmpty() || password.isNullOrEmpty()) {
        call.respond(HttpStatusCode.BadRequest, Response(null, requireFieldIsEmptyMessage(lang())))
        return null
    }
    return Pair(username.ignoreNull(), password.ignoreNull())
}
