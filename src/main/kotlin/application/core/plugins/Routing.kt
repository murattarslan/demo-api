package application.core.plugins

import io.ktor.server.application.*
import io.ktor.server.routing.*
import application.service.category.tools.categoryRouting
import application.service.media.tools.mediaRouting
import application.service.user.tools.userRouting
import application.service.user.tools.userRoutingForOnlyAdmin
import application.service.user.tools.userRoutingRequireToken

fun Application.configureRouting() {
    routing {
        mediaRouting()
        categoryRouting()

        userRouting()
        userRoutingRequireToken()
        userRoutingForOnlyAdmin()
    }
}
