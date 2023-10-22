package restaurant.core.plugins

import io.ktor.server.application.*
import io.ktor.server.routing.*
import restaurant.service.category.tools.categoryRouting
import restaurant.service.user.tools.userRouting
import restaurant.service.user.tools.userRoutingForOnlyAdmin
import restaurant.service.user.tools.userRoutingRequireToken

fun Application.configureRouting() {
    routing {
        categoryRouting()

        userRouting()
        userRoutingRequireToken()
        userRoutingForOnlyAdmin()
    }
}
