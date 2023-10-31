package application.service.category.tools

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.coroutines.runBlocking
import application.core.text.HeaderParams
import application.core.text.Locale
import application.service.category.endpoint.getAllCategory


fun Route.categoryRouting() {
    route("/category") {
        authenticate("auth-jwt") {
            get {
                val lang = call.request.headers[HeaderParams.language]
                val locale = Locale.entries.firstOrNull { it.lang.equals(lang, true) } ?: Locale.TURKISH
                val version = call.request.headers[HeaderParams.api_version]
                val response = runBlocking { getAllCategory(locale) }
                call.respond(
                    if (response.result == null)
                        HttpStatusCode.BadGateway
                    else
                        HttpStatusCode.OK,
                    response
                )
            }
        }
    }
}