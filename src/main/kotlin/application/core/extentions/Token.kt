package application.core.extentions

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.typesafe.config.ConfigFactory
import io.ktor.server.config.*

fun createToken(id: String, loginAt: String): String{
    val config = HoconApplicationConfig(ConfigFactory.load())
    val secret = config.property("jwt.secret").getString()
    val issuer = config.property("jwt.issuer").getString()
    val audience = config.property("jwt.audience").getString()
    return JWT.create()
        .withAudience(audience)
        .withIssuer(issuer)
        .withClaim("id", id)
        .withClaim("loginAt", loginAt)
        .sign(Algorithm.HMAC256(secret))
}