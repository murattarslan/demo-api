package restaurant.core.plugins

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.typesafe.config.ConfigFactory
import ignoreNull
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.config.*
import io.ktor.server.response.*
import kotlinx.coroutines.runBlocking
import restaurant.service.user.endpoint.checkUser

fun Application.configureAuthentication() {
    val config = HoconApplicationConfig(ConfigFactory.load())
    val secret = config.property("jwt.secret").getString()
    val issuer = config.property("jwt.issuer").getString()
    val audience = config.property("jwt.audience").getString()
    val myRealm = config.property("jwt.realm").getString()
    install(Authentication) {
        jwt("auth-jwt") {
            realm = myRealm
            verifier(JWT
                .require(Algorithm.HMAC256(secret))
                .withAudience(audience)
                .withIssuer(issuer)
                .build())
            validate { credential ->
                val id = credential.getClaim("id",String::class).ignoreNull()
                val loginAt = credential.getClaim("loginAt",String::class).ignoreNull()
                val (user, _) = runBlocking { checkUser(id) }
                if (user?.loginAt == loginAt) {
                    JWTPrincipal(credential.payload)
                } else {
                    null
                }
            }
            challenge { _,_ ->
                call.respond(HttpStatusCode.Unauthorized, "Token yok veya süresi doldu. lütfen tekrar giriş yapın")
            }
        }
        jwt("auth-jwt-for-admin") {
            realm = myRealm
            verifier(JWT
                .require(Algorithm.HMAC256(secret))
                .withAudience(audience)
                .withIssuer(issuer)
                .build())
            validate { credential ->
                val id = credential.getClaim("id",String::class).ignoreNull()
                val (user, _) = runBlocking { checkUser(id) }
                if (user?.id?.length.ignoreNull() < 30) {
                    JWTPrincipal(credential.payload)
                } else {
                    null
                }
            }
            challenge { _,_ ->
                call.respond(HttpStatusCode.Unauthorized, "Bu işlem için yetkiniz bulunmamaktadır")
            }
        }
    }
}