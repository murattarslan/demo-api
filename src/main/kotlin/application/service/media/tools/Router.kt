package application.service.media.tools

import io.ktor.server.auth.*
import io.ktor.server.routing.*


fun Route.mediaRouting() {
    route("/upload") {
        authenticate("auth-jwt") {
            post {
                handleFileUpload()
            }
        }
    }
}