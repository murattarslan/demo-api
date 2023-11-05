package application.service.user

import application.core.extentions.lang
import application.service.user.endpoint.all_users.deleteUser
import application.service.user.endpoint.all_users.deleteUserItemForAdmin
import application.service.user.endpoint.check_username.*
import application.service.user.endpoint.login.login
import application.service.user.endpoint.login.loginItem
import application.service.user.endpoint.logout.checkUserByToken
import application.service.user.endpoint.logout.logout
import application.service.user.endpoint.me.*
import application.service.user.endpoint.register.register
import application.service.user.endpoint.register.registerItem
import application.service.user.endpoint.users.getAllUsers
import application.service.user.endpoint.all_users.getAllUsersForAdmin
import application.service.user.endpoint.users.checkUserIdByParams
import application.service.user.endpoint.users.getUser
import ignoreNull
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.userRouting() {
    post("/login") {
        loginItem()?.let { (username, password) ->
            call.respond(login(username.ignoreNull(), password.ignoreNull(), lang()))
        }
    }
    post("/register") {
        registerItem()?.let {
            call.respond(register(it, lang()))
        }
    }
    post("/check-username") {
        checkUserNameItem()?.let {
            call.respond(checkUserName(it, lang()))
        }
    }
}

fun Route.userRoutingRequireToken() {
    authenticate("auth-jwt") {
        route("/me") {
            get {
                getUserIdItemByToken()?.let {
                    call.respond(getMe(it, lang()))
                }
            }
            put {
                updateUserItem()?.let {
                    call.respond(updateMe(it, lang()))
                }
            }
            delete {
                getUserIdItemByToken()?.let {
                    call.respond(deleteMe(it, lang()))
                }
            }
        }
        get("/logout") {
            checkUserByToken()?.let {
                call.respond(logout(it, lang()))
            }
        }
        route("/users") {
            get {
                call.respond(getAllUsers(lang()))
            }
            get("/{id}") {
                checkUserIdByParams()?.let {
                    call.respond(getUser(it, lang()))
                }
            }
        }
    }
}

fun Route.userRoutingForOnlyAdmin() {
    authenticate("auth-jwt-for-admin") {
        route("/all-users") {
            get {
                call.respond(getAllUsersForAdmin(lang()))
            }
            delete("/{id}") {
                deleteUserItemForAdmin()?.let { (isSafe, id) ->
                    call.respond(deleteUser(id, isSafe, lang()))
                }
            }
        }
    }
}
