package application.core.plugins

import com.typesafe.config.ConfigFactory
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import ignoreNull
import io.ktor.server.config.*
import application.core.extentions.toResultMessage
import application.core.model.Response
import application.core.model.ResultMessage
import application.core.text.Locale
import java.sql.ResultSet

val config = HikariConfig().apply {
    val config = HoconApplicationConfig(ConfigFactory.load())
    jdbcUrl = config.property("db.jdbcUrl").getString()
    username = config.property("db.username").getString()
    password = config.property("db.password").getString()
}

val dataSource = HikariDataSource(config)

fun <T>getQuery(sql: String, locale: Locale, resultMessage: ResultMessage, loop: (ResultSet) -> T?): Response<ArrayList<T>> {
    val result = arrayListOf<T>()
    var exception: Exception? = null
    val conn = try {
        dataSource.connection
    } catch (e: Exception) {
        return Response(null, e.toResultMessage(locale))
    }
    val statement = conn.createStatement()
    var resultSet: ResultSet? = null
    try {
        resultSet = statement.executeQuery(sql)
        while (resultSet.next()){
            loop(resultSet)?.let { result.add(it) }
        }
    } catch (e: Exception) {
        exception = e
    } finally {
        statement.close()
        if (resultSet?.isClosed.ignoreNull(true).not())
            resultSet?.close()
        conn.close()
    }
    val message = exception?.toResultMessage(locale) ?: resultMessage
    return Response(result, message)
}

fun updateQuery(sql: String, locale: Locale, resultMessage: ResultMessage): Response<Boolean> {
    var exception: Exception? = null
    val conn = try {
        dataSource.connection
    } catch (e: Exception) {
        return Response(null, e.toResultMessage(locale))
    }
    val statement = conn.createStatement()
    val result = try {
        statement.executeUpdate(sql)
    } catch (e: Exception) {
        exception = e
        0
    } finally {
        statement.close()
        conn.close()
    }
    val message = exception?.toResultMessage(locale) ?: resultMessage
    return Response(result != 0, message)
}
