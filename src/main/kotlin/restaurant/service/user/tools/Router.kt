package restaurant.service.user.tools

import ignoreNull
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.coroutines.runBlocking
import restaurant.core.model.Response
import restaurant.core.model.ResultMessage
import restaurant.core.model.ResultType
import restaurant.core.text.HeaderParams
import restaurant.core.text.Locale
import restaurant.service.user.endpoint.*
import restaurant.service.user.model.User

fun Route.userRouting() {
    route("/login") {
        post {
            val lang = call.request.headers[HeaderParams.language]
            val locale = Locale.entries.firstOrNull { it.lang.equals(lang, true) } ?: Locale.TURKISH
            val body = call.receive<HashMap<String, String>>()
            val username = body["username"]
            val password = body["password"]
            if (username.isNullOrEmpty() || password.isNullOrEmpty()) {
                val message = ResultMessage(
                    ResultType.FAILURE, "eksik bilgi", "zorunlu alanları doldurunuz"
                )
                call.respond(HttpStatusCode.BadRequest, Response(null, message))
            } else {
                val response = login(username.ignoreNull(), password.ignoreNull(), locale)
                call.respond(response)
            }
        }
    }
    route("/register") {
        post {
            val lang = call.request.headers[HeaderParams.language]
            val locale = Locale.entries.firstOrNull { it.lang.equals(lang, true) } ?: Locale.TURKISH
            val body = call.receive<HashMap<String, String>>()
            val name = body["name"]
            val lastname = body["lastname"]
            val username = body["username"]
            val password = body["password"]
            if (name.isNullOrEmpty() || lastname.isNullOrEmpty() || username.isNullOrEmpty() || password.isNullOrEmpty()) {
                val message = ResultMessage(
                    ResultType.FAILURE, "eksik bilgi", "zorunlu alanları doldurunuz"
                )
                call.respond(HttpStatusCode.BadRequest, Response(null, message))
            } else {
                val user = User(
                    "0", name.ignoreNull(), lastname.ignoreNull(), username.ignoreNull(), password.ignoreNull()
                )
                val (result, message) = runBlocking { register(user, locale) }
                if (result == null) call.respond(Response(null, message))
                else {
                    call.respond(Response(result, message))
                }
            }
        }
    }
    route("/check-username") {
        post {
            val lang = call.request.headers[HeaderParams.language]
            val locale = Locale.entries.firstOrNull { it.lang.equals(lang, true) } ?: Locale.TURKISH
            val body = call.receive<HashMap<String, String>>()
            val username = body["username"]
            if (username.isNullOrEmpty()) {
                val message = ResultMessage(
                    ResultType.FAILURE, "eksik bilgi", "zorunlu alanları doldurunuz"
                )
                call.respond(HttpStatusCode.BadRequest, Response(null, message))
            } else {
                val response = runBlocking { checkUserName(username, locale) }
                call.respond(response)
            }
        }
    }
}

fun Route.userRoutingRequireToken() {
    authenticate("auth-jwt") {
        get("/me") {
            val principal = call.principal<JWTPrincipal>()
            val id = principal?.payload?.getClaim("id")?.asString()
            val lang = call.request.headers[HeaderParams.language]
            val locale = Locale.entries.firstOrNull { it.lang.equals(lang, true) } ?: Locale.TURKISH
            call.respond(checkUser(id,locale))
        }
        put("/me") {
            val principal = call.principal<JWTPrincipal>()
            var id = principal?.payload?.getClaim("id")?.asString()
            val body = call.receive<HashMap<String, String>>()
            var nameBody = body["name"].ignoreNull()
            var lastnameBody = body["lastname"].ignoreNull()
            var phoneBody = body["phone"]
            var mailBody = body["mail"]
            val lang = call.request.headers[HeaderParams.language]
            val locale = Locale.entries.firstOrNull { it.lang.equals(lang, true) } ?: Locale.TURKISH
            val (user,message) = checkUser(id,locale)
            user?.apply {
                id = id.ignoreNull()
                name = nameBody
                lastname = lastnameBody
                phone = phoneBody
                mail = mailBody
            }
            if (user != null)
                call.respond(updateUser(user,locale))
            else
                call.respond(Response(null,message))
        }
        delete("/me") {
            val principal = call.principal<JWTPrincipal>()
            val id = principal?.payload?.getClaim("id")?.asString()
            val lang = call.request.headers[HeaderParams.language]
            val locale = Locale.entries.firstOrNull { it.lang.equals(lang, true) } ?: Locale.TURKISH
            if (id.isNullOrEmpty()) {
                val message = ResultMessage(
                    ResultType.FAILURE, "eksik bilgi", "zorunlu alanları doldurunuz"
                )
                call.respond(HttpStatusCode.BadRequest, Response(null, message))
            }
            else
                call.respond(safeDeleteUser(id,locale))
        }
        get("/users") {
            val lang = call.request.headers[HeaderParams.language]
            val locale = Locale.entries.firstOrNull { it.lang.equals(lang, true) } ?: Locale.TURKISH
            call.respond(getAllUsers(false, locale))
        }
        get("/users/{id}") {
            val userId = call.parameters["id"]
            val lang = call.request.headers[HeaderParams.language]
            val locale = Locale.entries.firstOrNull { it.lang.equals(lang, true) } ?: Locale.TURKISH
            call.respond(checkUser(userId,locale))
        }
    }
}

fun Route.userRoutingForOnlyAdmin() {
    authenticate("auth-jwt-for-admin") {
        get("/all-users") {
            val lang = call.request.headers[HeaderParams.language]
            val locale = Locale.entries.firstOrNull { it.lang.equals(lang, true) } ?: Locale.TURKISH
            call.respond(getAllUsers(true, locale))
        }
        delete("/users/{id}") {
            val principal = call.principal<JWTPrincipal>()
            val id = principal?.payload?.getClaim("id")?.asString()
            val userId = call.parameters["id"]
            val body = call.receive<HashMap<String, Boolean>>()
            val isSafe = body["isSafe"].ignoreNull(true)
            val lang = call.request.headers[HeaderParams.language]
            val locale = Locale.entries.firstOrNull { it.lang.equals(lang, true) } ?: Locale.TURKISH
            if (id.isNullOrEmpty() || userId.isNullOrEmpty()) {
                val message = ResultMessage(
                    ResultType.FAILURE, "eksik bilgi", "zorunlu alanları doldurunuz"
                )
                call.respond(HttpStatusCode.BadRequest, Response(null, message))
            }
            else
                call.respond(deleteUserGateway(id.ignoreNull(),userId.ignoreNull(),locale, isSafe))
        }
    }
}
