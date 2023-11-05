package application.core.plugins

import io.ktor.server.application.*
import io.ktor.server.routing.*
import application.service.media.tools.mediaRouting
import application.service.user.userRouting
import application.service.user.userRoutingForOnlyAdmin
import application.service.user.userRoutingRequireToken

fun Application.configureRouting() {
    routing {
        mediaRouting()

        userRouting()
        userRoutingRequireToken()
        userRoutingForOnlyAdmin()
    }
}
