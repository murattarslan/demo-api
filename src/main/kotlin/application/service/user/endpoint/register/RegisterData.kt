package application.service.user.endpoint.register

import application.core.extentions.lang
import application.core.model.Response
import application.service.user.model.User
import ignoreNull
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.util.pipeline.*

suspend fun PipelineContext<Unit, ApplicationCall>.registerItem(): User?{
    val body = call.receive<HashMap<String, String>>()
    val name = body["name"]
    val lastname = body["lastname"]
    val username = body["username"]
    val password = body["password"]
    if (name.isNullOrEmpty() || lastname.isNullOrEmpty() || username.isNullOrEmpty() || password.isNullOrEmpty()) {
        call.respond(HttpStatusCode.BadRequest, Response(null, requireFieldIsEmptyMessage(lang())))
        return null
    }
    return User(
        "0",
        name.ignoreNull(),
        lastname.ignoreNull(),
        username.ignoreNull(),
        password.ignoreNull()
    )
}