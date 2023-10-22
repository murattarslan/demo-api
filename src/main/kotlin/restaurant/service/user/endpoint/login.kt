package restaurant.service.user.endpoint

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.typesafe.config.ConfigFactory
import ignoreNull
import io.ktor.server.config.*
import restaurant.core.extentions.format
import restaurant.core.model.Response
import restaurant.core.model.ResultMessage
import restaurant.core.model.ResultType
import restaurant.core.plugins.getQuery
import restaurant.core.text.Locale
import restaurant.service.user.model.User
import restaurant.service.user.tools.toUser
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
        updateUserForLogin(user, locale)
    }
}

fun wrongPasswordMessage(locale: Locale = Locale.TURKISH) = ResultMessage(
    ResultType.INFO,
    locale.language.checkUserWrongPasswordTitle,
    locale.language.checkUserWrongPasswordMessage
)