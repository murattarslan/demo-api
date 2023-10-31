package application.service.user.endpoint.update

import application.core.extentions.lang
import application.core.model.Response
import application.core.model.ResultMessage
import application.core.model.ResultType
import application.core.text.Locale
import application.service.user.model.User
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.util.pipeline.*


suspend fun PipelineContext<Unit, ApplicationCall>.updateUserItem(): User?{
    val principal = call.principal<JWTPrincipal>()
    var id = principal?.payload?.getClaim("id")?.asString()
    val body = call.receive<HashMap<String, String>>()
    val nameBody = body["name"]
    val lastnameBody = body["lastname"]
    val phoneBody = body["phone"]
    val mailBody = body["mail"]
    if (id.isNullOrEmpty()) {
        call.respond(HttpStatusCode.BadRequest, Response(null, requireFieldIsEmptyMessage(lang())))
        return null
    }
    return User(
        id = id,
        name = nameBody,
        lastname = lastnameBody,
        phone = phoneBody,
        mail = mailBody
    )
}
