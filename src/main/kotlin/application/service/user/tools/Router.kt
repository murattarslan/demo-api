package application.service.user.tools

import application.core.extentions.lang
import application.service.user.endpoint.check.*
import application.service.user.endpoint.delete.deleteUser
import application.service.user.endpoint.delete.deleteUserItemForAdmin
import application.service.user.endpoint.delete.safeDeleteUser
import application.service.user.endpoint.get.getAllUsers
import application.service.user.endpoint.get.getAllUsersWithDeleted
import application.service.user.endpoint.get.getUser
import application.service.user.endpoint.login.login
import application.service.user.endpoint.login.loginItem
import application.service.user.endpoint.logout.logout
import application.service.user.endpoint.register.register
import application.service.user.endpoint.register.registerItem
import application.service.user.endpoint.update.updateUser
import application.service.user.endpoint.update.updateUserItem
import ignoreNull
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.userRouting() {
    route("/login") {
        post {
            loginItem()?.let { (username, password) ->
                call.respond(login(username.ignoreNull(), password.ignoreNull(), lang()))
            }
        }
    }
    route("/register") {
        post {
            registerItem()?.let {
                call.respond(register(it, lang()))
            }
        }
    }
    route("/check-username") {
        post {
            checkUserNameItem()?.let {
                call.respond(checkUserName(it, lang()))
            }
        }
    }
}

fun Route.userRoutingRequireToken() {
    authenticate("auth-jwt") {
        get("/me") {
            checkUserIdItemByToken()?.let {
                call.respond(getUser(it, lang()))
            }
        }
        put("/me") {
            updateUserItem()?.let {
                call.respond(updateUser(it, lang()))
            }
        }
        delete("/me") {
            checkUserIdItemByToken()?.let {
                call.respond(safeDeleteUser(it, lang()))
            }
        }
        get("/logout") {
            checkUserIdItemByToken()?.let {
                call.respond(logout(it, lang()))
            }
        }
        get("/users") {
            call.respond(getAllUsers(lang()))
        }
        get("/users/{id}") {
            checkUserIdItemByParams()?.let {
                call.respond(getUser(it, lang()))
            }
        }
    }
}

fun Route.userRoutingForOnlyAdmin() {
    authenticate("auth-jwt-for-admin") {
        get("/all-users") {
            call.respond(getAllUsersWithDeleted(lang()))
        }
        delete("/users/{id}") {
            deleteUserItemForAdmin()?.let { (isSafe, id) ->
                if (isSafe)
                    call.respond(safeDeleteUser(id, lang()))
                else
                    call.respond(deleteUser(id, lang()))
            }
        }
    }
}
