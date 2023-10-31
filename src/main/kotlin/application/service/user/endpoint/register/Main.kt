package application.service.user.endpoint.register

import application.core.extentions.createId
import application.core.extentions.format
import application.core.model.Response
import application.core.model.ResultType
import application.core.plugins.updateQuery
import application.core.text.Locale
import application.service.user.endpoint.login.login
import application.service.user.model.User
import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.typesafe.config.ConfigFactory
import ignoreNull
import io.ktor.server.config.*
import java.util.*

fun register(
    user: User,
    locale: Locale = Locale.TURKISH
): Response<User> {
    val config = HoconApplicationConfig(ConfigFactory.load())
    val secret = config.property("jwt.secret").getString()
    val issuer = config.property("jwt.issuer").getString()
    val audience = config.property("jwt.audience").getString()
    user.id = createId()
    user.loginAt = Date().format()
    user.token = JWT.create()
        .withAudience(audience)
        .withIssuer(issuer)
        .withClaim("id", user.id)
        .withClaim("loginAt", user.loginAt)
        .sign(Algorithm.HMAC256(secret))
    val response = updateQuery(
        "insert into users values (" +
                " '${user.id}'," +
                " '${user.name}'," +
                " '${user.lastname}'," +
                " '${user.username}'," +
                " '${user.password}'," +
                " null," +
                " null," +
                " false," +
                " '${Date().format()}'," +
                " '${Date().format()}'," +
                " '${user.token}'," +
                " '${user.loginAt}'," +
                " 3" +
                ")",
        locale,
        registerSuccessMessage(locale)
    )
    return if (response.resultMessage.type == ResultType.ERROR)
        Response(null, response.resultMessage)
    else if (response.result.ignoreNull().not())
        Response(null, registerFailMessage(locale))
    else
        Response(user, registerSuccessMessage(locale))
}