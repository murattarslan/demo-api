package restaurant.application

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import restaurant.core.plugins.configureAuthentication
import restaurant.core.plugins.configureRouting
import restaurant.core.plugins.configureSerialization

fun main() {
    embeddedServer(Netty, port = 8080, host = "127.0.0.1", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    configureAuthentication()
    configureSerialization()
    configureRouting()
}
