package application.service.user.endpoint.login

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.typesafe.config.ConfigFactory
import ignoreNull
import io.ktor.server.config.*
import application.core.extentions.format
import application.core.model.Response
import application.core.model.ResultMessage
import application.core.model.ResultType
import application.core.plugins.getQuery
import application.core.plugins.updateQuery
import application.core.text.Locale
import application.service.user.model.User
import application.service.user.tools.toUser
import java.util.*


fun login(
    username: String,
    password: String,
    locale: Locale = Locale.TURKISH
): Response<User> {
    val response = getQuery(
        "select * from users where username = '$username' and is_delete = false",
        locale,
        successMessage(locale)
    ){ it.toUser() }
    return if (response.resultMessage.type == ResultType.ERROR)
        Response(null, response.resultMessage)
    else if (response.result.isNullOrEmpty())
        Response(null, userNotFoundMessage(locale))
    else if (response.result.ignoreNull().none { it.password == password })
        Response(null, wrongPasswordMessage(locale))
    else {
        val user = response.result.ignoreNull().first { it.password == password }
        val config = HoconApplicationConfig(ConfigFactory.load())
        val secret = config.property("jwt.secret").getString()
        val issuer = config.property("jwt.issuer").getString()
        val audience = config.property("jwt.audience").getString()
        user.loginAt = Date().format()
        user.token = JWT.create()
            .withAudience(audience)
            .withIssuer(issuer)
            .withClaim("id", user.id)
            .withClaim("loginAt", user.loginAt)
            .sign(Algorithm.HMAC256(secret))

        val booleanResponse = updateQuery(
            "update users set " +
                    "login_at = '${user.loginAt}', " +
                    "modified_at = '${Date().format()}', " +
                    "token = '${user.token}' " +
                    "where id = '${user.id}'",
            locale,
            successMessage(locale)
        )
        if (booleanResponse.resultMessage.type == ResultType.ERROR)
            Response(null, booleanResponse.resultMessage)
        else if (booleanResponse.result.ignoreNull().not())
            Response(null, userNotFoundMessage(locale))
        else
            Response(user, successMessage(locale))
    }
}